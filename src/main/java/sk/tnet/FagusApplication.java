package sk.tnet;

import org.apache.camel.spring.boot.CamelSpringBootApplicationController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class FagusApplication {

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(FagusApplication.class, args);
		CamelSpringBootApplicationController applicationController =
	            applicationContext.getBean(CamelSpringBootApplicationController.class);
	    applicationController.run();
	}

}
