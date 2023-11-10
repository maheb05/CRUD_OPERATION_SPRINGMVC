package com.tap.airport.dao;

import java.util.List;

import com.tap.airport.entity.AirportEntity;


public interface AirportDAO {
	 void saveAirportEntity(AirportEntity entity);
	 
	 AirportEntity getAirportEntityById(int id);
	 
	 Long getCountOfRunways();
	 
	 void updateAirportEntity();
	 
	 void updateAirportByName(String name);
	 
	 void updateAirportById(int id);
	 
	 void updateAirportByLocation(String location);
	 	 
	 void deleteAirportById(int id);
	 
	 List<AirportEntity> getAllAirportentity();
	 
}
