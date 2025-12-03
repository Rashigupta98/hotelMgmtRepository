package com.restapiproject.hotelMgmt.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restapiproject.hotelMgmt.dto.HotelDto;
import com.restapiproject.hotelMgmt.model.Hotel;
import com.restapiproject.hotelMgmt.service.HotelService;
import com.restapiproject.hotelMgmt.service.HotelService1;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/hotels")
public class HotelController {
	
	private final HotelService hotelService;
	public HotelController(HotelService hotelService) {
		this.hotelService=hotelService;
	} 
	//dtotoentity
	private Hotel dtoEntity(HotelDto dto) {
		Hotel h= new Hotel();
		h.setId(dto.getId());
		h.setName(dto.getName());
		h.setAddress(dto.getAddress());
		h.setTotal_rooms(dto.getTotal_rooms());
		h.setAvailable_rooms(dto.getAvailable_rooms());
		h.setPrice_per_night(dto.getPrice_per_night());
		return h;
		
	}
	//entitytodto
	private HotelDto entityToDto(Hotel h) {
		HotelDto dto = new HotelDto();
		dto.setId(h.getId());
		dto.setName(h.getName());
		dto.setAddress(h.getAddress());
		dto.setTotal_rooms(h.getTotal_rooms());
		dto.setAvailable_rooms(h.getAvailable_rooms());
		dto.setPrice_per_night(h.getPrice_per_night());
	return dto;
	}
	
	//get request
	@GetMapping
	public ResponseEntity<List<HotelDto>> getAll() {
		List<Hotel> list =  hotelService.getAllHotels();
		//convert list og Hotel entities into corresponding dtos
		List<HotelDto> dtoList = list.stream().map(this::entityToDto).collect(Collectors.toList());
		//return an http response
		//ResponseEntity- spring wraper for HTTP Response
		return ResponseEntity.ok(dtoList);
	}
	
	//Get request for specific hotel - id
	@GetMapping("/{id}")
	public ResponseEntity<HotelDto> getbyId(@PathVariable Long id) {
		//@PathVariable - tell spring to extract {id}
		//- from URL and assign it to id variable
		// /api/hotels/4
		Hotel h =hotelService.getHotelById(id);
		HotelDto dto = entityToDto(h);
		return ResponseEntity.ok(dto);		
	}

	@PostMapping
	public ResponseEntity<HotelDto> create(@Valid @RequestBody HotelDto dto) {
		// @RequestBody -> tell the spring to take data from request body
		// give data in request body -> JSON
		// take data - into dto
		// deserilize it into java object
		// @valid -> check for non empty values, like price>0, min>?
		// if validation fails -> 400 bad request
		Hotel h = dtoEntity(dto);
 
		// Save entity using service layer
		Hotel created =hotelService.createHotel(h);
 
		// Build URI for the newly created resource
		URI location = URI.create("/api/hotels/" + created.getId());
 
		// Return HTTP 201 Created with Location header and DTO body
		return ResponseEntity.created(location).body(entityToDto(created));
	}
	// put request 
	@PutMapping("/{id}")
	public ResponseEntity<HotelDto> update(@PathVariable Long id,@Valid @RequestBody HotelDto dto) {
		Hotel h = dtoEntity(dto);
		Hotel updated = hotelService.updateHotel(id, h);
		return ResponseEntity.ok(entityToDto(updated));
	}
	// delete request
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
	    hotelService.deleteHotel(id);
	    return ResponseEntity.noContent().build();
	}
}
