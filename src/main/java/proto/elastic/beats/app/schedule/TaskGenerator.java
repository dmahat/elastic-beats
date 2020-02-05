package proto.elastic.beats.app.schedule;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;

import proto.elastic.beats.app.task.Task;



public class TaskGenerator {
	private Logger logger = LoggerFactory.getLogger(TaskGenerator.class);
	
	public TaskExecutor getTaskExecutor() {
		return taskExecutor;
	}


	public void setTaskExecutor(TaskExecutor taskExecutor) {
		this.taskExecutor = taskExecutor;
	}

	private TaskExecutor taskExecutor;



	
	public void execute() {

		for(int i = 0; i < 25; i++) {
		      taskExecutor.execute(new Task());
		}

	}



}
