package com.seat.demo.adapters.rest.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.seat.demo.entities.controller.Order;
import com.seat.demo.entities.database.CarOrder;
import com.seat.demo.services.CarOrderServices;
import com.seat.micro.kafka.entities.Command;
import com.seat.micro.kafka.entities.Event;
import com.seat.micro.kafka.factories.KafkaMessageFactory;
import com.seat.micro.kafka.factories.MessageFactory;

@RestController
@RequestMapping({ "/api/v1" })
public class VehicleManagementToolController {
	
	private Logger logger = LoggerFactory.getLogger(VehicleManagementToolController.class);
	
	@Autowired
	private KafkaTemplate<String, Command> commandKafkaTemplate;
	
	@Autowired
	private KafkaTemplate<String, Event> eventKafkaTemplate;
	
	@Autowired
	private ObjectMapper mapper;
	
	@Autowired
	private CarOrderServices carOrderServices;
	
	@RequestMapping(value = "/order", method = RequestMethod.POST)
	public String createNewOrder(@RequestBody Order order) throws Exception {

		//- Guardamos la información en el modelo de datos local del microservicio
		if(carOrderServices != null && order != null) {
			CarOrder response = carOrderServices.addNewCarOrder(order);
			
			if(response != null) {
				order.setId(response.getId());
				logger.info("===> CREATED ORDER: response.getId(): " + order.getId());
			}
		}
		
		//- Lanzamos un "Command" para el alta de la orden en el microservicio del TrackingTool
		if(commandKafkaTemplate != null) {			
			MessageFactory factory = KafkaMessageFactory.getInstance();
			
			Command command = factory.createCommandMessageProcesor().createNewEmptyCommandV1();
			command.getHeader().setMessageAction("create-order");
			command.setData(mapper.writeValueAsString(order));
			
			commandKafkaTemplate.send("micro-tracking-tool-command-topic", command);
		}

		return "Endpoint -- [CREATE_NEW_ORDER] ACCESSED!!";
	}
	
	@RequestMapping(value = "/order", method = RequestMethod.PUT)
	public String updateOrder(@RequestBody Order order) throws Exception {
		
		//- Guardamos la información en el modelo de datos local del microservicio
		if(carOrderServices != null && order != null) {
			carOrderServices.updateCarOrder(order);
			
			logger.info("===> UPDATED ORDER: order.getId(): " + order.getId());
		}

		if(eventKafkaTemplate != null) {		
			MessageFactory factory = KafkaMessageFactory.getInstance();
			
			Event event = factory.createEventMessageProcesor().createNewEmptyEventV1();

			event.getHeader().setMessageAction("update-order");
			event.setData(mapper.writeValueAsString(order));

			eventKafkaTemplate.send("micro-vehicle-management-tool-event-topic", event);
		}

		return "Endpoint -- [UPDATE_ORDER] ACCESSED!!";
	}
	
    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public List<CarOrder> getAllOrders() throws Exception {
    	List<CarOrder> response = new ArrayList<CarOrder>();
    	
    	if(carOrderServices != null) {
    		response = carOrderServices.getAllCarOrders();
    	}
    	
    	return response;
    }
}
