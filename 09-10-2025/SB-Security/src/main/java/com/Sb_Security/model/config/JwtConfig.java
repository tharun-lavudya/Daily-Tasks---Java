package com.Sb_Security.model.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.Sb_Security.model.token.JwtUtil;

@Configuration
public class JwtConfig {

	@Bean
	public JwtUtil jwtUtil() {
		return new JwtUtil();
	}
}
