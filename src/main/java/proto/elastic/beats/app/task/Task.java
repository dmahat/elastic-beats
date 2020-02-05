package proto.elastic.beats.app.task;

import java.util.Random;
import java.util.UUID;

import org.slf4j.MDC;

import proto.elastic.beats.app.notify.EventLogger;



public class Task implements Runnable{
	private static volatile long TOTAL = 0;
	
	private String taskId = UUID.randomUUID().toString().replace("-", "");
	private long instanceId = 0;
	
	public Task() {
		taskId = UUID.randomUUID().toString().replace("-", "");
		instanceId = TOTAL++;
	}
	
	public void run() {
		initMDCContext();
		startTask();
		try {
			Thread.sleep((new Random()).nextInt(5000));
		} catch (InterruptedException e) {
			// Ignore
		}
		endTask();
	}
	
	private void startTask() {
		EventLogger.getInstance().logMessage("SUBMISSION,"+taskId+","+"STARTED");
	}
	
    private void endTask() {
    	//1/20th of tasks will end in errors
		if(instanceId%20 == 0) {
			EventLogger.getInstance().logMessage("SUBMISSION,"+taskId+","+"FAILED");
		}
		else {
			EventLogger.getInstance().logMessage("SUBMISSION,"+taskId+","+"COMPLETED");
		}
	}

	public void initMDCContext() {
		String uuid = UUID.randomUUID().toString().replace("-", "");
		MDC.put("correlationId", uuid);
		MDC.put("requestId", uuid);
	}
	

	public void clearLogContext() {
		MDC.clear();
	}
}
