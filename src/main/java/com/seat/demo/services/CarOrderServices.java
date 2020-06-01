package com.seat.demo.services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seat.demo.entities.controller.Order;
import com.seat.demo.entities.database.CarOrder;
import com.seat.demo.repositories.CarOrderRepository;
import com.seat.demo.utils.VehicleManagementToolUtils;

@Service
public class CarOrderServices {
	
	private Logger logger = LoggerFactory.getLogger(CarOrderServices.class);
	
	@Autowired
	private CarOrderRepository carOrderRepo;

	public CarOrder addNewCarOrder(Order order) {
		CarOrder response = new CarOrder();
		
		if(carOrderRepo != null && order != null) {
			
			CarOrder carOrder = new CarOrder();
			carOrder.setModelo(order.getModelo());
			carOrder.setAnnoModelo(order.getAnnoModelo());
			carOrder.setGrupoModelo(order.getGrupoModelo());
			carOrder.setFirstName(order.getFirstName());
			carOrder.setLastName(order.getLastName());
			carOrder.setPhoneNumber(order.getPhoneNumber());
			carOrder.setColor(order.getColor());
			carOrder.setPrs(order.getPrs());
			carOrder.setCommercialNumber(VehicleManagementToolUtils.getCommercialNumber());
			
			response = carOrderRepo.save(carOrder);
		}
		
		return response;
	}
	
	public void updateCarOrder(Order order) {
		if(carOrderRepo != null && order != null) {
			
			CarOrder carOrder = new CarOrder();
			carOrder.setId(order.getId());
			carOrder.setModelo(order.getModelo());
			carOrder.setAnnoModelo(order.getAnnoModelo());
			carOrder.setGrupoModelo(order.getGrupoModelo());
			carOrder.setFirstName(order.getFirstName());
			carOrder.setLastName(order.getLastName());
			carOrder.setPhoneNumber(order.getPhoneNumber());
			carOrder.setColor(order.getColor());
			carOrder.setPrs(order.getPrs());
			carOrder.setPrs(order.getPrs());
			carOrder.setCommercialNumber(order.getCommercialNumber());
			
			carOrderRepo.save(carOrder);
		}
	}
	
	public List<CarOrder> getAllCarOrders() {
		List<CarOrder> response = new ArrayList<CarOrder>();
		
		if(carOrderRepo != null) {
			carOrderRepo.findAll().forEach(order -> response.add(order));
		}
		
		return response;
	}
	
}
