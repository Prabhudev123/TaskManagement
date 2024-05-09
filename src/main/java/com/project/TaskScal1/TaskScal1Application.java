package com.project.TaskScal1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class TaskScal1Application {

	public static void main(String[] args) {
		SpringApplication.run(TaskScal1Application.class, args);
		System.out.println("Hello Prabhu!");
	}

}
