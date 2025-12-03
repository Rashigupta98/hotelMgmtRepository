package com.restapiproject.hotelMgmt.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.restapiproject.hotelMgmt.exception.ResourceNotFoundException;
import com.restapiproject.hotelMgmt.model.Hotel;
import com.restapiproject.hotelMgmt.repository.HotelDao;

@Service
public class HotelService implements HotelService1  {


	private final HotelDao hoteldao;
	
	public HotelService(HotelDao hoteldao) {
		this.hoteldao=hoteldao;
	}
	@Override	
	public Hotel createHotel(Hotel hotel) {
		// TODO Auto-generated method stub
		return hoteldao.save(hotel);
	}
    @Override
	public Hotel getHotelById(Long id) {
		// TODO Auto-generated method stub
		return hoteldao.findById(id).orElseThrow(()->
		new ResourceNotFoundException("hotel not found with id: "+id));
	}
    @Override
	public List<Hotel> getAllHotels() {
		// TODO Auto-generated method stub
		return hoteldao.findAll();
	}
    @Override
	public Hotel updateHotel(Long id,Hotel hotel) {
		// TODO Auto-generated method stub
		Hotel existing = getHotelById(id);
		existing.setName(hotel.getName());
		existing.setAddress(hotel.getAddress());
		existing.setTotal_rooms(hotel.getTotal_rooms());
		existing.setAvailable_rooms(hotel.getAvailable_rooms());
		existing.setPrice_per_night(hotel.getPrice_per_night());
		int rows = hoteldao.update(existing);
		if(rows<=0) throw new RuntimeException("update failed for hotel id: "+id);
		
		return existing;
	}
    @Override
	public void deleteHotel(Long id) {
		// TODO Auto-generated method stub
		getHotelById(id);
		int rows= hoteldao.deleteById(id);
		if(rows<=0) throw new RuntimeException("delete failed for hotel id"+id);	
	}

}
