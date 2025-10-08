package com.order.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class Inventory {

	
    //private final TestOrder testorder;

    private final List<String> messages = new ArrayList<>();

    @KafkaListener(topics = "${topic.inventory}", groupId = "inventory_group")
    public void consumer(String message) {
        System.out.println("Received Order: " + message);
        messages.add(message);
    }

    public List<String> getMessages() {
        return messages;
    }
}
