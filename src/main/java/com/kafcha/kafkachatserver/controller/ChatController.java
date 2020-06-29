package com.kafcha.kafkachatserver.controller;

import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kafcha.kafkachatserver.constants.KafkaConstants;
import com.kafcha.kafkachatserver.model.Message;

@RestController
public class ChatController{
	
	@Autowired
	public KafkaTemplate<String,Message> kafkaTemplate;
	
	
	@PostMapping(value="/api/send",consumes="application/json",produces="application/json")
	public void sendMessage(@RequestBody Message message) {
		message.setTimestamp(LocalDateTime.now().toString());
		try {
		kafkaTemplate.send(KafkaConstants.KAFKA_TOPIC, message);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}	
		
	}
	
	@MessageMapping("/sendMessage")
	@SendTo("/topic/group")
	public Message broadcastGroupMessage(@Payload Message message) {
		return message;
	}
	
}
