package com.kafkaC.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {
	
	private final List<String> messages = new ArrayList<>();
	
	@KafkaListener(topics = "Producer_Topic", groupId = "Producer_group")
	public void consumer(String message) {
		System.out.println("Received: " + message);
		messages.add(message);
	}
	
	public List<String> getMessages(){
		return messages;
	}

}
