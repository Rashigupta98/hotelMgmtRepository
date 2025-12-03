package com.restapiproject.hotelMgmt.controller;

public class FeatureController {
	String name;

	public FeatureController(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "FeatureController [name=" + name + "]";
	}
	

}
