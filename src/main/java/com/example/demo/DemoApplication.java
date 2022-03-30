package com.example.demo;

import com.example.demo.controllers.PersonControllerApi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;


//@EnableWebFlux
@SpringBootApplication
@ComponentScan(basePackages = "com.example.demo")
@EnableR2dbcRepositories(basePackages = "com.example.demo.infra.repository")
//@EnableAutoConfiguration(exclude = {WebMvcAutoConfiguration.class })
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
