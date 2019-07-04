package gms.control.supreme;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DeadLineCheckLogger implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Logger> loggers;

	
	
	public DeadLineCheckLogger() {
		if(loggers==null)
			loggers=new ArrayList<Logger>() ;
	}



	public List<Logger> getLoggers() {
		return loggers;
	}
}
