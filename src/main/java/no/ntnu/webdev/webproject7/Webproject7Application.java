package no.ntnu.webdev.webproject7;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class Webproject7Application {

	public static void main(String[] args) {
		SpringApplication.run(Webproject7Application.class, args);
	}

}
