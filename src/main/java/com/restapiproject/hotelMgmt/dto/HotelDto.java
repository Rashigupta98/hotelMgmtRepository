package com.restapiproject.hotelMgmt.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

import jakarta.validation.*;
public class HotelDto {
	
	//model- representing a table - hotel
	//directly maps to table - my jdbc
	
	//DTO data transfer object
	//used for communication b/wlayers controller -service-client
	//used to validate user input w/o exposing internal db structure
	//make your apis independent of db changes
	//customize what data goes out,comes in -> created at

	//postman/frontend-> controller layer(accepts hoteldto)-> validation happens
	            //                                                     |
	             //                         return response   <-   repository layer <- service layer
	// convert hotel entity to hoteldto save entity in db (convert hoteldto -> hotel entity)
	
	private Long id;
	@NotBlank(message="name is required")
	private String name;
	
	@NotBlank(message="Address is required")
	private String address;
	
	
	@NotNull(message="total_rooms is required")
	@Min(value=1, message="total_rooms must be >=1")
	private int total_rooms;
	
	@NotNull(message="available_rooms is required")
	@Min(value=1, message="available_rooms must be >=0")
	private int available_rooms;
	
	@NotNull(message="price_per_night is required")
	@DecimalMin(value="0.0", message="price_per_night must be >=0.0")
	private BigDecimal price_per_night;

	public HotelDto() {}
	
	public HotelDto(Long id, @NotBlank(message = "name is required") String name,
			@NotBlank(message = "Address is required") String address,
			@NotNull(message = "total_rooms is required") @Min(value = 1, message = "total_rooms must be >=1") int total_rooms,
			@NotNull(message = "available_rooms is required") @Min(value = 1, message = "available_rooms must be >=0") int available_rooms,
			@NotNull(message = "price_per_night is required") @DecimalMin(value = "0.0", message = "price_per_night must be >=0.0") BigDecimal price_per_night) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.total_rooms = total_rooms;
		this.available_rooms = available_rooms;
		this.price_per_night = price_per_night;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getTotal_rooms() {
		return total_rooms;
	}

	public void setTotal_rooms(int total_rooms) {
		this.total_rooms = total_rooms;
	}

	public int getAvailable_rooms() {
		return available_rooms;
	}

	public void setAvailable_rooms(int available_rooms) {
		this.available_rooms = available_rooms;
	}

	public BigDecimal getPrice_per_night() {
		return price_per_night;
	}

	public void setPrice_per_night(BigDecimal price_per_night) {
		this.price_per_night = price_per_night;
	}
	
	
	
}
