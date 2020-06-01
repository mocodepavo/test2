package com.seat.demo.adapters.kafka;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.seat.micro.kafka.KafkaPlatform;
import com.seat.micro.kafka.entities.Command;
import com.seat.micro.kafka.entities.Event;
import com.seat.micro.kafka.entities.Response;


@Configuration
public class KafkaProducerConfig extends KafkaPlatform {
 
    @Bean
    public ProducerFactory<String, String> plaintTextProducerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, getKafkaBootstraping());
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        
        return new DefaultKafkaProducerFactory<>(props);
    }
    
    @Bean
    public ProducerFactory<String, Command> commandProducerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, getKafkaBootstraping());
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        
        return new DefaultKafkaProducerFactory<>(props);
    }
    
    @Bean
    public ProducerFactory<String, Response> responseProducerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, getKafkaBootstraping());
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        
        return new DefaultKafkaProducerFactory<>(props);
    }
    
    @Bean
    public ProducerFactory<String, Event> eventProducerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, getKafkaBootstraping());
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        
        return new DefaultKafkaProducerFactory<>(props);
    }
    
    @Bean
    public ProducerFactory<String, Error> errorProducerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, getKafkaBootstraping());
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        
        return new DefaultKafkaProducerFactory<>(props);
    }   

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        return new KafkaTemplate<>(plaintTextProducerFactory());
    }
    
    @Bean
    public KafkaTemplate<String, Command> commandKafkaTemplate() {
        return new KafkaTemplate<>(commandProducerFactory());
    }
  
    @Bean
    public KafkaTemplate<String, Response> responseKafkaTemplate() {
        return new KafkaTemplate<>(responseProducerFactory());
    }
    
    @Bean
    public KafkaTemplate<String, Event> eventKafkaTemplate() {
        return new KafkaTemplate<>(eventProducerFactory());
    }
    
    @Bean
    public KafkaTemplate<String, Error> errorKafkaTemplate() {
        return new KafkaTemplate<>(errorProducerFactory());
    }
}
