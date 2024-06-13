package com.asha.employeeService1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class EmployeeService1Application {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeService1Application.class, args);
		System.out.println("employee service started successively");
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
