package com.hero.eid;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.hero")
public class EIDValidatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(EIDValidatorApplication.class, args);
	}
}
