package com.seat.demo.components.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.seat.demo.services.CarOrderServices;
import com.seat.micro.kafka.entities.Event;
import com.seat.micro.kafka.logic.EventMessageLogic;

@Component
public class MicroVehicleManagementEventMessageLogic implements EventMessageLogic {
	
	private Logger logger = LoggerFactory.getLogger(MicroVehicleManagementEventMessageLogic.class);
	
	@Autowired
	private CarOrderServices carOrderServices;
	
	@Autowired
	public ObjectMapper mapper;

	@Override
	public void processEvent(Event event) {
		
		if(event != null) {
			logger.info("Message: " + event.getData());
		}
	}
}
