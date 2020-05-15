package com.kafcha.kafkachatserver.configuration;

import com.kafcha.kafkachatserver.model.*;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

@EnableKafka
@Configuration
public class ProducerConfiguration{
	@Bean
	public ProducerFactory<String,Message> producerFactory(){
		return new DefaultKafkaProducerFactory<>(producerConfigurations());
	}

	private Map<String,Object> producerConfigurations() {
		Map<String, Object> configuration = new HashMap<>();
//		configuration.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,KafkaConstants.Kafka_BROKER);
		configuration.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,StringSerializer.class);
		configuration.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,JsonSerializer.class);
		return configuration;
	}
	
	@Bean
	private KafkaTemplate<String, Message> kafkaTemplate(){
		return new KafkaTemplate<>(producerFactory());
	}
}