package com.kafcha.kafkachatserver.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import com.kafcha.kafkachatserver.constants.KafkaConstants;
import com.kafcha.kafkachatserver.model.Message;

@Component
public class MessageListener{
	@Autowired
	SimpMessagingTemplate template;
	
	@KafkaListener(topics= KafkaConstants.KAFKA_TOPIC,groupId = KafkaConstants.GROUP_ID)
	public void listen(Message message) {
		System.out.print("sending via listener");
		template.convertAndSend("/topic/group",message);
	}
		
}