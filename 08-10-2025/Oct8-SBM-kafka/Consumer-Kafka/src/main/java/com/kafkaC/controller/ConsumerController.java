package com.kafkaC.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kafkaC.service.ConsumerService;

@RestController
@RequestMapping("/consumer")
public class ConsumerController {
	
	@Autowired
	private ConsumerService consumerService;
	
	@GetMapping("/getMsg")
	public List<String> getMsg(){
		return consumerService.getMessages();
	}

}
