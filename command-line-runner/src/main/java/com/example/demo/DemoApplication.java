package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

	@Bean
	public CommandLineRunner run(String s){
		System.out.println(s);
		return args -> System.out.println("I am a runner");
	}

	@Bean
	public String exampleOfBean(){
		return "Hello!! it's me";
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
