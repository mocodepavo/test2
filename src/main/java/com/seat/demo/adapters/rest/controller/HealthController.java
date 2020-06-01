package com.seat.demo.adapters.rest.controller;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class HealthController implements HealthIndicator {

	@Override
	public Health health() {
		Health health = Health.up().withDetail("Details","Server is up").build();
		return health;
	}
}
