package com.restapiproject.hotelMgmt.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.restapiproject.hotelMgmt.model.Hotel;
import com.restapiproject.hotelMgmt.util.HotelRowMapper;


@Repository  //mark this class as DAO component - spring automatically detect for DI, directly interacts with db
public class HotelDaoImp1 implements HotelDao{
	
	private final JdbcTemplate jdbcTemplate;
	
	public HotelDaoImp1(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate=jdbcTemplate;
	}

	@Override
	public Hotel save(Hotel hotel) {
		String sql="insert into hotels(id,name,address,total_rooms,available_rooms,price_per_night) values (?,?,?,?,?,?) ";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(connection->{
				PreparedStatement ps = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			ps.setFloat(1, hotel.getId());
			ps.setString(2, hotel.getName());
			ps.setString(3,hotel.getAddress());
			ps.setInt(4, hotel.getTotal_rooms());
			ps.setInt(5,hotel.getAvailable_rooms());
			ps.setBigDecimal(6, hotel.getPrice_per_night());
			ps.setTimestamp(6, java.sql.Timestamp.valueOf(hotel.getCreated_at()));
			ps.setTimestamp(6, java.sql.Timestamp.valueOf(hotel.getUpdated_at()));
			
			return ps;
		},keyHolder);
		hotel.setId(keyHolder.getKey().longValue());
		return hotel;  //hotel object along with its generated key
		
	}

	@Override
	public Optional<Hotel> findById(Long id) {
		// TODO Auto-generated method stub
		String sql="select * from hotels where id=?";
		List<Hotel> list = jdbcTemplate.query(sql, new HotelRowMapper(),id);
		return list.isEmpty()?Optional.empty():Optional.of(list.get(0));
	}

	@Override
	public List<Hotel> findAll() {
		// TODO Auto-generated method stub
		String sql="select * from hotels";
		List<Hotel> list = jdbcTemplate.query(sql, new HotelRowMapper());
		return list;
	}

	@Override
	public int update(Hotel hotel) {
		// TODO Auto-generated method stub
		String sql="update hotels SET id=?,name=?,address=?,total_rooms=?,available_rooms=?,price_per_night=?";
		return jdbcTemplate.update(sql,
				hotel.getId(),
				hotel.getName(),
				hotel.getAddress(),
				hotel.getTotal_rooms(),
				hotel.getAvailable_rooms(),
				hotel.getPrice_per_night());
		
		
	}

	@Override
	public int deleteById(Long id) {
		// TODO Auto-generated method stub
		String sql="delete from hotels where id=?";
		return jdbcTemplate.update(sql,id);
	}
}
