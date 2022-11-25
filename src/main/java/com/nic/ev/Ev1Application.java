package com.nic.ev;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

@SuppressWarnings("unused")
@SpringBootApplication
public class Ev1Application extends SpringBootServletInitializer{

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	public static void main(String[] args) {
		SpringApplication.run(Ev1Application.class, args);
		System.out.println("ifms_integration");
	}
	
//	@Override
//	  protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//	      return builder.sources(Ev1Application.class);
//	  }

}
