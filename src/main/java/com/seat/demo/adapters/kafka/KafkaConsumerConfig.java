package com.seat.demo.adapters.kafka;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.seat.micro.kafka.KafkaPlatform;
import com.seat.micro.kafka.entities.Command;
import com.seat.micro.kafka.entities.Event;
import com.seat.micro.kafka.entities.Response;

@EnableKafka
@Configuration
public class KafkaConsumerConfig extends KafkaPlatform {
	
	private Logger logger = LoggerFactory.getLogger(KafkaConsumerConfig.class);
 
    @Bean
    public ConsumerFactory<String, String> plaintTextConsumerFactory() {
        Map<String, Object> props = new HashMap<>();
        
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, getKafkaBootstraping());
        props.put(ConsumerConfig.GROUP_ID_CONFIG, getKafkaGroupId());
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(props);
    }
 
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(plaintTextConsumerFactory());
        return factory;
    }
    
    @Bean
    public ConsumerFactory<String, Command> commandConsumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, getKafkaBootstraping());
        props.put(ConsumerConfig.GROUP_ID_CONFIG, getKafkaGroupId());
        
        return new DefaultKafkaConsumerFactory<>(
        	      props,
        	      new StringDeserializer(), 
        	      new JsonDeserializer<>(Command.class));
    }
    
    @Bean
    public ConsumerFactory<String, Response> responseConsumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, getKafkaBootstraping());
        props.put(ConsumerConfig.GROUP_ID_CONFIG, getKafkaGroupId());
        
        return new DefaultKafkaConsumerFactory<>(
        	      props,
        	      new StringDeserializer(), 
        	      new JsonDeserializer<>(Response.class));
    }
    
    @Bean
    public ConsumerFactory<String, Event> eventConsumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, getKafkaBootstraping());
        props.put(ConsumerConfig.GROUP_ID_CONFIG, getKafkaGroupId());
        
        return new DefaultKafkaConsumerFactory<>(
        	      props,
        	      new StringDeserializer(), 
        	      new JsonDeserializer<>(Event.class));
    }
    
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Command> commandKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Command> factory = new ConcurrentKafkaListenerContainerFactory<String, Command>();
        factory.setConsumerFactory(commandConsumerFactory());
        return factory;
    }
    
    
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Response> responseKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Response> factory = new ConcurrentKafkaListenerContainerFactory<String, Response>();
        factory.setConsumerFactory(responseConsumerFactory());
        return factory;
    }
    
    @KafkaListener(topics = "micro-vehicle-management-tool-response-topic", containerFactory = "responseKafkaListenerContainerFactory")
    public void responseListener(Response response) {
    	
    	if(response != null) {
    		logger.info(">> response.getRelatedEntityID(): " + response.getHeader().getRelatedEntityID());
    		logger.info(">> response.getData(): " + response.getData());
    	}
    	
    }
}