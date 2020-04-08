package com.yun;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {

		System.out.println(Double.valueOf("12.12312412412512").intValue());
		SpringApplication.run(Application.class, args);
	}

}
