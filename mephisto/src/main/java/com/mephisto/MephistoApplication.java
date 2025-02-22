package com.mephisto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

@SpringBootApplication
@Controller
public class MephistoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MephistoApplication.class, args);
	}

	@GetMapping("/")
	public ResponseEntity<String> home() {
		String message = "<h1>Welcome to Mephisto!</h1><p>You've found the hidden path!</p>";
		return new ResponseEntity<>(message, HttpStatus.OK);
	}
}
