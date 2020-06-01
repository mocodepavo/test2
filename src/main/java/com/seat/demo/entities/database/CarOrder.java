/*
 * Copyright (C) 2020 SEAT, S.A - All Rights Reserved
 *
 * This file is part of MicroServices Chassis.
 *
 * Unauthorized reproduction, copying, or modification, of this file, via
 * any medium is strictly prohibited.
 *
 * This code is Proprietary and Confidential.
 *
 * All the 3rd parties libraries included in the project are regulated by
 * their own licenses.
 */

package com.seat.demo.entities.database;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
@Entity
@Table(name="car_order")
public class CarOrder implements Serializable {
	private static final long serialVersionUID = 1L;
	
    @Id
    @GeneratedValue
    private int id;
    
    @Column(name = "firstName", nullable = false)
    private String firstName;
    
    @Column(name = "lastName", nullable = false)
    private String lastName;
    
    @Column(name = "phoneNumber", nullable = false)
    private String phoneNumber;
    
    @Column(name = "modelo", nullable = false)
    private String  modelo;
    
    @Column(name = "grupoModelo", nullable = false)
    private String  grupoModelo;
    
    @Column(name = "annoModelo", nullable = false)
    private String annoModelo;
    
    @Column(name = "color", nullable = false)
    private String color;
    
    @Column(name = "prs", nullable = false)
    private String prs;
    
    @Column(name = "commercialNumber", nullable = false)
    private String commercialNumber;
    
    //getters and setters
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public String getGrupoModelo() {
		return grupoModelo;
	}
	public void setGrupoModelo(String grupoModelo) {
		this.grupoModelo = grupoModelo;
	}
	public String getAnnoModelo() {
		return annoModelo;
	}
	public void setAnnoModelo(String annoModelo) {
		this.annoModelo = annoModelo;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getPrs() {
		return prs;
	}
	public void setPrs(String prs) {
		this.prs = prs;
	}
	public String getCommercialNumber() {
		return commercialNumber;
	}
	public void setCommercialNumber(String commercialNumber) {
		this.commercialNumber = commercialNumber;
	}
	
}
