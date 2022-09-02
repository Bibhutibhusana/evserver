package com.nic.ev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class Ev1Application extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(Ev1Application.class, args);
		System.out.println("ifms_integration");
	}
	
//	@Override
//	  protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//	      return builder.sources(Ev1Application.class);
//	  }

}
