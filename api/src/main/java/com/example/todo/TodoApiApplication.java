package com.example.todo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main application class for the Todo API. This is the entry point of the Spring Boot application.
 * The application provides RESTful APIs for managing users and tasks.
 */
@SpringBootApplication
public class TodoApiApplication {

  /**
   * Main method that starts the Spring Boot application.
   *
   * @param args Command line arguments passed to the application
   */
  public static void main(String[] args) {
    SpringApplication.run(TodoApiApplication.class, args);
  }
}
