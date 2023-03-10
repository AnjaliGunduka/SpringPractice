package com.javatechie.spring.cloud.task.api;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.task.configuration.EnableTask;
import org.springframework.cloud.task.listener.TaskExecutionListener;
import org.springframework.cloud.task.listener.annotation.AfterTask;
import org.springframework.cloud.task.listener.annotation.BeforeTask;
import org.springframework.cloud.task.listener.annotation.FailedTask;
import org.springframework.cloud.task.repository.TaskExecution;

@SpringBootApplication
@EnableTask
public class SpringCloudTaskExampleApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudTaskExampleApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Welcome to " + args[0]);

	}

	// Invoked after the TaskExecution has been stored in the TaskRepository
	@BeforeTask
	public void start(TaskExecution taskExecution) {
		System.out.println("TaskName : " + taskExecution.getTaskName() + " Execution Id : "
				+ taskExecution.getExecutionId() + " started...");

	}

// Invoked after the TaskExecution has been updated in the TaskRepository, upon task end
	@AfterTask
	public void end(TaskExecution taskExecution) {
		System.out.println("TaskName : " + taskExecution.getTaskName() + " Execution Id : "
				+ taskExecution.getExecutionId() + " completed...");

	}

// Invoked if an uncaught exception occurs during task execution.
	@FailedTask
	public void fail(TaskExecution taskExecution, Throwable throwable) {
		System.out.println("TaskName : " + taskExecution.getTaskName() + " Execution Id : "
				+ taskExecution.getExecutionId() + " failed...");

	}

}
