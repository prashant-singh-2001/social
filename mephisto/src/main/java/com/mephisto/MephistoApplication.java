package com.mephisto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

/**
 * Main application class for Mephisto application.
 * 
 * This class serves as the entry point for the Spring Boot application.
 * It also provides a simple welcome endpoint.
 * 
 * @author Prashant Singh
 *         on February 24, 2025.
 */
@SpringBootApplication
@Controller
public class MephistoApplication {

	/**
	 * Main method to run the Spring Boot application.
	 * 
	 * @param args command-line arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(MephistoApplication.class, args);
	}

	/**
	 * Welcome endpoint.
	 * 
	 * @return a welcome message
	 */
	@GetMapping("/")
	public ResponseEntity<String> welcome() {
		String message = "Welcome to the Mephisto API!";
		return new ResponseEntity<>(message, HttpStatus.OK);
	}

}
