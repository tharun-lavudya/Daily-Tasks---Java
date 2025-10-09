package com.Sb_Security.model.hellocontroller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	
	@GetMapping("/api/user")
	public String UserAccess() {
		return "Hello User! You are authenticated!!";
	}
	
	@GetMapping("/api/admin")
	public String adminAccess() {
		return "Hello Admin! You are authenticated!!";
	}

}

