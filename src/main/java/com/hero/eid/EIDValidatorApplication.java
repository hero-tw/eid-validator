package com.hero.eid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.hero")
public class EIDValidatorApplication {


	@Autowired
	private Config config;


	public static void main(String[] args) {
		SpringApplication.run(EIDValidatorApplication.class, args);
	}
}
