package com.mephisto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
@Controller
public class MephistoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MephistoApplication.class, args);
	}

	@GetMapping("/")
	@ResponseBody
	public String home() {
		return "<h1>Welcome to Mephisto!</h1><p>You've found the hidden path!</p>";
	}
}
