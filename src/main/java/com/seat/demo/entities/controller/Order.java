package com.seat.demo.entities.controller;

public class Order {
	
	private int id;
	private String firstName;
	private String lastName;
	private String phoneNumber;
    private String modelo;
    private String grupoModelo;
    private String annoModelo;
    private String color;
    private String prs;
    private String commercialNumber;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
