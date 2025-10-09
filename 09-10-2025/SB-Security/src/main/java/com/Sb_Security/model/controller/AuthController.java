package com.Sb_Security.model.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Sb_Security.model.token.JwtUtil;

import lombok.Data;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	

	@Data
	static class AuthRequest{
		private String username;
		private String password;	
	}
	
	@PostMapping("/login")
	public String login(@RequestBody AuthRequest request) {
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		if(authentication.isAuthenticated()) {
			return jwtUtil.generateToken(request.getUsername());
		}
		else {
			throw new RuntimeException("Invalid credentials");
		}
	}

}
