package proto.elastic.beats.app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Application {

	public static void main(String[] args) {
		String []springConfig = { "app-context.xml"};
		//load Spring IOC. The configured Scheduler and Executor Service does the rest.
		ApplicationContext context = new ClassPathXmlApplicationContext(springConfig);

	}

}
