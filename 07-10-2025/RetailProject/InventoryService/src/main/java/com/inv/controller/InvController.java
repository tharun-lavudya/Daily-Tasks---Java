package com.inv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inv")
public class InvController {
	
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	@Value("${topic.inventory}")
	private String InventoryTopic;
	
	@PostMapping("/create")
	public String createOrder(@RequestBody String OrderDetails) {
		kafkaTemplate.send(InventoryTopic,OrderDetails);
		return "Order placed successfully";
	
	}
}
