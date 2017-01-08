package com.spring.boot.fileupload.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
@EnableAutoConfiguration
@ComponentScan({"com.spring.boot.fileupload"})
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	 
}
