package com.seat.demo.repositories;

import org.springframework.data.repository.CrudRepository;

import com.seat.demo.entities.database.CarOrder;

public interface CarOrderRepository extends CrudRepository<CarOrder, Integer> {}

