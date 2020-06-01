package com.seat.demo.components.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.seat.demo.entities.database.CarOrder;
import com.seat.demo.services.CarOrderServices;
import com.seat.micro.kafka.entities.Command;
import com.seat.micro.kafka.entities.Response;
import com.seat.micro.kafka.logic.CommandMessageLogic;
import com.seat.micro.kafka.processors.ResponseMessageProcessor;

@Component
public class MicroVehicleManagementCommandMessageLogic implements CommandMessageLogic {

	private Logger logger = LoggerFactory.getLogger(MicroVehicleManagementCommandMessageLogic.class);
		
	@Autowired
	private CarOrderServices carOrderServices;
	
	@Autowired
	public ObjectMapper mapper;
	
	@Override
	public Response processCommand(Command command, ResponseMessageProcessor responseProcessor) {
		
		Response response = null;
		
		try {
			
			if(command != null && responseProcessor != null && mapper != null && carOrderServices != null) {				
				CarOrder order = mapper.readValue(command.getData(), CarOrder.class);
		    	
		    	response = responseProcessor.createResponseV1FromCommandV1(command);
			}
			
		} catch (JsonMappingException ex) {
			logger.error("ERROR: " + ex.getMessage());
			
		} catch (JsonProcessingException ex) {
			logger.error("ERROR: " + ex.getMessage());
		}
		
		return response;
	}

}
