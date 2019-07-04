package gms.control.supreme;

import java.io.FileInputStream;  
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;
import java.util.List;

public class LoggerManager {

	private DeadLineCheckLogger log;
	private String logPath="E:\\GitThubRepository\\GDOUGSM\\log";
	
	public LoggerManager() {
		if(log==null)
			log=new DeadLineCheckLogger();
	}

	public LoggerManager(String logPath) {
		super();
		this.logPath = logPath;
	}
	public String getLogPath() {
		return logPath;
	}

	public void setLogPath(String logPath) {
		this.logPath = logPath;
	}
	
	public void open() {
		try {
			ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(logPath));
			log=(DeadLineCheckLogger) objectInputStream.readObject();
			if(log==null) {
				log=new DeadLineCheckLogger();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void close() {
		try {
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(logPath));
			objectOutputStream.writeObject(log);
			objectOutputStream.flush();
			objectOutputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void write(Logger logger) {
		log.getLoggers().add(logger);
	}

	/*日志输出格式
	 * 	
	 * 	DeadLineCheck-Log:logs' number is 
	 *  
	 *  log 1 
	 *  startTime:   endTime 
	 *  Event Modal deadline check result:
	 *  Evnets:
	 *  
	 *  Evnet Applications:
	 *  
	 *  Field Modal deadline check result:
	 *  Field Applications:
	 *  
	 *  Equipment Modal deadline check result:
	 *  Equipment Applications:
	 */
	public void read() {
		System.out.println("DeadLineCheck-Log:logs' number is "+log.getLoggers().size());
		Iterator<Logger> iterator = log.getLoggers().iterator();
		int count=1;
		while(iterator.hasNext()) {
			Logger next = iterator.next();
			Iterator<?> deeperIterator;
			System.out.println("log "+count);
			System.out.println("startTime:"+next.getStartTime()+" endTime:"+next.getEndTime());
			
			System.out.println("Event Modal deadline check result:");
			System.out.println("Evnets:");
			List<Integer> handledEvents = next.getHandledEvents();
			deeperIterator= handledEvents.iterator();
			while(deeperIterator.hasNext()) {
				System.out.print("ID:"+(int)deeperIterator.next()+" ");
			}
			
			System.out.println("Evnet Applications:");
			List<Integer> handledApplications = next.getHandledApplications();
			deeperIterator= handledApplications.iterator();
			while(deeperIterator.hasNext()) {
				System.out.print("ID:"+(int)deeperIterator.next()+" ");
			}
			
/*			System.out.println("Field Modal deadline check result:");
			System.out.println("Field Applications:");
			List<> handledApplications = next.getHandledFieldApplications();
			deeperIterator= handledEvents.iterator();
			while(deeperIterator.hasNext()) {
				Event n = (Event)deeperIterator.next();
				System.out.println(n.toString());
			}
			
			System.out.println("Equipment Modal deadline check result:");
			System.out.println("Equipment Applications:");
			List<> handledApplications = next.getHandledEquipmentApplications();
			deeperIterator= handledApplications.iterator();
			while(deeperIterator.hasNext()) {
				Event n = (Event)deeperIterator.next();
				System.out.println(n.toString());
			}*/
		}
	}

}
