package proto.elastic.beats.app.notify;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EventLogger {
	
	private Logger logger = LoggerFactory.getLogger(EventLogger.class);
	
	private static EventLogger instance = new EventLogger();
   
	public static EventLogger getInstance() {
		return instance;
		
	}
	
	public void logMessage(String msg) {
		logger.info(msg);
	}
}
