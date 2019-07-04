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
	
	private Calendar date;//����ʹ��Calendar��ȡʱ�䣬Date���Ѳ�����ʹ��
	private int minStartHour=0;//���翪ʼ��Сʱ
	private int maxStartHour=0;//����ʼ��Сʱ
	private LoggerManager loggerManager=new LoggerManager();
	
	private volatile List<Integer> overdueEventIDs=null;//��������
	private volatile List<Integer> overdueEventApplicationIDs=null;//�����������붩��
	private List<Integer> overdueFieldApplicationIDs;//���ڳ������붩��

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
	 * ������ڶ���
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
	 * ϵͳ�������ڼ��
	 * ����ͬһ�ű���˿���ͬʱ�½������߳̽���
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
	 * ϵͳ���ڶ�������
	 * ע�⣺Ӧ�ô���������ģ����ٴ����غ����ģ���Ϊ���¶����г��غ����Ķ����������Ϊ�˱������ݵ�һ���ԣ�Ӧ����ɾ���������
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
			}//�ȴ����´�����Ϻ��ٴ����غ�����
//			new Thread(new FieldModalHandle(overdueFieldApplicationIDs)).start();
//			new Thread(new EquipmentModalHandle(overdueEquipmentApplicationIDs)).start();
		}
		
	}

	/**
	 * ����ģ�鶩�����ڼ�飬�������������overdueEventIDs��overdueEventApplicationIDs������
	 * ����ͬһ�ű���˿���ͬʱ�½��������߳̽���
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
	 * ����ģ����ڴ���
	 * ��Ϊ���º�����������ڴ�����ͬһ�ű���˿���ͬʱ�½������߳̽���
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
						//��Ϣ֪ͨ����
						EventInform inform=new EventInform();
						inform.setDate(new Date(System.currentTimeMillis()));
						inform.setContent(ep.getContent()+"����ʧ�ܣ�������ڣ����������롣");
						inform.setUserID(ep.getUserID());
						inform.setState(0);
						informService.addEventInform(inform);
					}
				}
			}).start();
		}
		
	}
	
	/**
	 * ���ض������ڼ��
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
	 * ���ع��ڶ�������
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
	 * ���㵽��һ��ĺ�����
	 * @param current
	 * @return
	 * @throws Exception
	 */
	private long computeTime(Calendar current) throws Exception {
		int year = current.get(Calendar.YEAR);
		int month = current.get(Calendar.MONTH)+1;//һ�¶�Ӧ����0
		int day = current.get(Calendar.DAY_OF_MONTH);
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
		long tomorrow0clock = sdf.parse(NextDate.nextDate(year, month, day)).getTime();
		System.out.println(NextDate.nextDate(year, month, day)+"~"+year+"-"+month+"-"+day+"="+(tomorrow0clock-current.getTimeInMillis()));
		return tomorrow0clock-current.getTimeInMillis();
	}
	
	/**
	 * ������һ��
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
			//�ж�����Ƿ�Ϸ�
			if(year>=1912&&year<=2050) {
				//�ж�����Ƿ������꣬��2�·������й�
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
				//�ж��·��Ƿ�Ϸ�
				if(month>0&&month<=12) {
					//�ж������Ƿ�Ϸ�
					if(date>0) {
						//31����·���Ҫ��date>31�����·ݽ�λ
						if(oddMonth.contains(month)) {
							if(date>31) {
								throw new Exception("�Ƿ����ڣ�");
							}
							date++;//��һ�죬���ڼ�һ;
							if(date>31) {
								date=1;
								month++;
							}
						//���·�����ʱ��Ҫ��date>29ʱ���·ݽ�λ����ƽ��ʱ��Ҫ��date>28ʱ���·ݽ�λ
						}else if(month==2){
							if(isLeapYear) {
								if(date>29) {
									throw new Exception("�Ƿ����ڣ�");
								}
								date++;//��һ�죬���ڼ�һ
								if(date>29) {
									date=1;
									month++;
								}
							}else {
								if(date>28) {
									throw new Exception("�Ƿ����ڣ�");
								}
								date++;//��һ�죬���ڼ�һ
								if(date>28) {
									date=1;
									month++;
								}
							}
						}else {
							//��ͨ�·�30��
							if(date>30) {
								throw new Exception("�Ƿ����ڣ�");
							}
							date++;//��һ�죬���ڼ�һ
							if(date>30) {
								date=1;
								month++;
							}
						}
						//�·�>12ʱ����ݽ�λ
						if(month>12) {
							month=1;
							year++;
						}
						return new String(year+"-"+month+"-"+date);
					}else {
						throw new Exception("�Ƿ����ڣ�");
					}
				}else {
					throw new Exception("�Ƿ��·ݣ�");
				}
			}else {
				throw new Exception("�Ƿ���ݣ�");
			}
		}
	}
}
