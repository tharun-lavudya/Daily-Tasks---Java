package com.kafkaP.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kafkaP.service.ProducerService;

@RestController
@RequestMapping("/producer")
public class ProducerController {

	@Autowired
	private ProducerService producerService;
	
	@PostMapping("/sendMsg")
	public String sendMsg(@RequestBody String message) {
		producerService.sendMessage(message);
		return "Message sent from Producer";
	}
	
}
