package gms.control.supreme;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import gms.entry.event.EventApplication;
import gms.entry.event.EventInform;
import gms.service.equip.OrdercreateImpl;
import gms.service.equip.OrdercreateService;
import gms.service.event.IEventApplicationService;
import gms.service.event.IEventInformService;
import gms.service.event.IEventService;

@SuppressWarnings("deprecation")
@Controller
public class CheckThread {

	@Resource(name="EventService")
	private IEventService eventService;
	@Resource(name="EventApplicationService")
	private IEventApplicationService applicationService;
	@Resource(name="EventInformService")
	private IEventInformService informService;
	@Resource(name="OrdercreateImpl")
	private OrdercreateService equipmentService;
	
	private Calendar date;//必须使用Calendar获取时间，Date类已不建议使用
	private int minStartHour=0;//最早开始的小时
	private int maxStartHour=0;//最晚开始的小时
	private LoggerManager loggerManager=new LoggerManager();
	
	private volatile List<Integer> overdueEventIDs=null;//过期赛事
	private volatile List<Integer> overdueEventApplicationIDs=null;//过期赛事申请订单
	private List<Integer> overdueFieldApplicationIDs;//过期场地申请订单

	@PostConstruct
	public void check() {
		new Thread(new DeadlineProcess()).start();
	}
/*	@PostConstruct
	public void test() {
		Thread thread = new Thread(new EventModalCheck());
		thread.start();
		System.out.println(new Timestamp(System.currentTimeMillis()));
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(new Timestamp(System.currentTimeMillis()));
//		System.out.println(overdueEventIDs.size());
//		System.out.println(overdueEventApplicationIDs.size());
		System.out.println("end");
	}*/
	
	/**
	 * 处理过期订单
	 * 
	 * @author www25
	 *
	 */
	class DeadlineProcess implements Runnable{
		@Override
		public void run() {
			while(!Thread.currentThread().isInterrupted()) {
				date = Calendar.getInstance();
				int hour = date.get(Calendar.HOUR);
				try {
					if(hour>=minStartHour&&hour<=maxStartHour) {
						Thread checkThread = new Thread(new DeadlineCheck());
						checkThread.start();
						checkThread.join();
						loggerManager.open();
						Logger newLogger = new Logger();
						newLogger.setStartTime(date.getTime());
						newLogger.setHandledEvents(overdueEventIDs);
						newLogger.setHandledApplications(overdueEventApplicationIDs);
						Thread thread = new Thread(new DeadlineHandle());
						thread.start();
						while(thread.isAlive());
						newLogger.setEndTime(Calendar.getInstance().getTime());		
						loggerManager.write(newLogger);
						loggerManager.close();
					}
					Thread.sleep(computeTime(Calendar.getInstance()));
				} catch (InterruptedException e) {
					e.printStackTrace();
					Thread.currentThread().interrupt();
				} catch (Exception e) {
					e.printStackTrace();
					Thread.currentThread().interrupt();
				}
			}
		}
		
	}
	
	/**
	 * 系统订单过期检查
	 * 不在同一张表，因此可以同时新建三个线程进行
	 * @author www25
	 *
	 */
	class DeadlineCheck implements Runnable{

		@Override
		public void run() {
			new Thread(new EventModalCheck()).start();
//			new Thread(new FieldModalCheck()).start();
//			new Thread(new EquipmentModalCheck()).start();
			while(overdueEventIDs==null&&overdueEventApplicationIDs==null);
		}
		
	}
	
	/**
	 * 系统过期订单处理
	 * 注意：应该处理完赛事模块后再处理场地和器材，因为赛事订单有场地和器材订单的外键，为了保持数据的一致性，应该先删除外键引用
	 * @author www25
	 *
	 */
	class DeadlineHandle implements Runnable{

		@Override
		public void run() {
			Thread eventThread = new Thread(new EventModalHandle());
			eventThread.start();
			try {
				eventThread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}//等待赛事处理完毕后再处理场地和器材
//			new Thread(new FieldModalHandle(overdueFieldApplicationIDs)).start();
//			new Thread(new EquipmentModalHandle(overdueEquipmentApplicationIDs)).start();
		}
		
	}

	/**
	 * 赛事模块订单过期检查，并将结果保存在overdueEventIDs，overdueEventApplicationIDs变量中
	 * 不在同一张表，因此可以同时新建两个个线程进行
	 * @author www25
	 *
	 */
	class EventModalCheck implements Runnable{
		@Override
		public void run() {
			new Thread(new Runnable() {
				@Override
				public void run() {
					System.out.println("check1");
					overdueEventIDs=eventService.getOverdueEventIDs();
					System.out.println("run");
				}
			}).start();
			new Thread(new Runnable() {
				@Override
				public void run() {
					System.out.println("check2");
					overdueEventApplicationIDs=applicationService.getOverdueEventApplicationIDs();
					System.out.println("run");
				}
			}).start();
			
		}
		
	}
	
	/**
	 * 赛事模块过期处理
	 * 因为赛事和赛事申请过期处理不在同一张表，因此可以同时新建两个线程进行
	 * @author www25
	 *
	 */
	class EventModalHandle implements Runnable{
		@Override
		public void run() {
			new Thread(new Runnable() {
				@Override
				public void run() {
					System.out.println(overdueEventIDs.size());
					Iterator<Integer> iterator = overdueEventIDs.iterator();
					while(iterator.hasNext()) {
						eventService.overdueEvent(iterator.next());
					}
				}
			}).start();
			new Thread(new Runnable() {
				@Override
				public void run() {
					System.out.println(overdueEventApplicationIDs.size());
					Iterator<Integer> iterator =overdueEventApplicationIDs.iterator();
					while(iterator.hasNext()) {
						EventApplication ep=applicationService.refuseEventApplication(iterator.next());
						//消息通知处理
						EventInform inform=new EventInform();
						inform.setDate(new Date(System.currentTimeMillis()));
						inform.setContent(ep.getContent()+"申请失败！申请过期，请重新申请。");
						inform.setUserID(ep.getUserID());
						inform.setState(0);
						informService.addEventInform(inform);
					}
				}
			}).start();
		}
		
	}
	
	/**
	 * 场地订单过期检查
	 * @author www25
	 *
	 */
	class FieldModalCheck implements Runnable{

		@Override
		public void run() {
			new Thread(new Runnable() {
				@Override
				public void run() {
//					overdueFieldApplicationIDs=fieldService.getOverdueFieldApplicationIDs();
				}
			}).start();
		}
		
	}
	/**
	 * 场地过期订单处理
	 * @author www25
	 *
	 */
	class FieldModalHandle implements Runnable{

		private List<Integer> overdueFieldApplicationIDs;
		
		public FieldModalHandle(List<Integer> overdueFieldApplicationIDs) {
			super();
			this.overdueFieldApplicationIDs = overdueFieldApplicationIDs;
		}
		@Override
		public void run() {
			new Thread(new Runnable() {
				@Override
				public void run() {
					Iterator<Integer> iterator =overdueFieldApplicationIDs.iterator();
					while(iterator.hasNext()) {
/*						EventApplication ep=fieldService.refuseEventApplication(iterator.next());
*/
					}
				}
			}).start();
		}
		
	}
	

	class EquipmentModalHandle implements Runnable{

		@Override
		public void run() {
			new Thread(new Runnable() {
				@Override
				public void run() {
					equipmentService.systemcheck();
				}
			}).start();
		}
		
	}
	
	/**
	 * 计算到下一天的毫秒数
	 * @param current
	 * @return
	 * @throws Exception
	 */
	private long computeTime(Calendar current) throws Exception {
		int year = current.get(Calendar.YEAR);
		int month = current.get(Calendar.MONTH)+1;//一月对应的是0
		int day = current.get(Calendar.DAY_OF_MONTH);
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
		long tomorrow0clock = sdf.parse(NextDate.nextDate(year, month, day)).getTime();
		System.out.println(NextDate.nextDate(year, month, day)+"~"+year+"-"+month+"-"+day+"="+(tomorrow0clock-current.getTimeInMillis()));
		return tomorrow0clock-current.getTimeInMillis();
	}
	
	/**
	 * 计算下一天
	 * @author www25
	 *
	 */
	static class NextDate {
		public static List<Integer> oddMonth=new LinkedList<Integer>();
		static {
			oddMonth.add(1);
			oddMonth.add(3);
			oddMonth.add(5);
			oddMonth.add(7);
			oddMonth.add(8);
			oddMonth.add(10);
			oddMonth.add(12);
		}	
		public static String nextDate(int year,int month,int date) throws Exception {
			//判断年份是否合法
			if(year>=1912&&year<=2050) {
				//判断年份是否是闰年，与2月份日期有关
				boolean isLeapYear=false;
				if(year%100==0) {
					if(year%400==0) {
						isLeapYear=true;
					}
				}else {
					if(year%4==0) {
						isLeapYear=true;
					}
				}
				//判断月份是否合法
				if(month>0&&month<=12) {
					//判断日期是否合法
					if(date>0) {
						//31天的月份需要在date>31是向月份进位
						if(oddMonth.contains(month)) {
							if(date>31) {
								throw new Exception("非法日期！");
							}
							date++;//下一天，日期加一;
							if(date>31) {
								date=1;
								month++;
							}
						//二月份闰年时需要在date>29时向月份进位，在平年时需要在date>28时向月份进位
						}else if(month==2){
							if(isLeapYear) {
								if(date>29) {
									throw new Exception("非法日期！");
								}
								date++;//下一天，日期加一
								if(date>29) {
									date=1;
									month++;
								}
							}else {
								if(date>28) {
									throw new Exception("非法日期！");
								}
								date++;//下一天，日期加一
								if(date>28) {
									date=1;
									month++;
								}
							}
						}else {
							//普通月份30天
							if(date>30) {
								throw new Exception("非法日期！");
							}
							date++;//下一天，日期加一
							if(date>30) {
								date=1;
								month++;
							}
						}
						//月份>12时向年份进位
						if(month>12) {
							month=1;
							year++;
						}
						return new String(year+"-"+month+"-"+date);
					}else {
						throw new Exception("非法日期！");
					}
				}else {
					throw new Exception("非法月份！");
				}
			}else {
				throw new Exception("非法年份！");
			}
		}
	}
}
