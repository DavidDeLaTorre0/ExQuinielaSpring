package org.agaray.exQuiniela;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class ExQuinielaApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(ExQuinielaApplication.class, args);
	}

}
