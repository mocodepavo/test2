package com.seat.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.seat.micro.configuration.ConfigurationValidator;

@SpringBootApplication
public class MicroVehicleManagementToolApplication {
	
	static Logger logger = LoggerFactory.getLogger(MicroVehicleManagementToolApplication.class);
	
	public static void main(String[] args) throws Exception {
		ConfigurationValidator cv = new ConfigurationValidator();
		
		if(cv.isConfigurationOK()) {
			SpringApplication.run(MicroVehicleManagementToolApplication.class, args);
		}
	}
}
