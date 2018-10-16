package com.hero.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class EIDValidatorApplication {


	@Autowired
	ConfigurableEnvironment env;


	public static void main(String[] args) {
		SpringApplication.run(EIDValidatorApplication.class, args);
	}
}
