package com.tap.airport.service;

import java.util.Objects;

import org.springframework.stereotype.Component;

import com.tap.airport.entity.AirportEntity;

@Component("airportEntityValidation")
public class AirportEntityValidation implements Validations {


	public boolean validateAirportEntity(AirportEntity entity) {
		
		if(entity == null) {
			throw new IllegalArgumentException("Empty record cannot be accepted");
		}
		
		boolean flag = false;
		
		if(Objects.nonNull(entity.getAirportName()) && !entity.getAirportName().isEmpty() && !entity.getAirportName().isBlank()) {
			flag = true;
		}else {
			System.out.println("Enter airport name do not leave it empty");
		}
		
		
		if(Objects.nonNull(entity.getAirportLocation()) && !entity.getAirportLocation().isEmpty() && !entity.getAirportLocation().isBlank()) {
			flag = true;
		}else {
			System.out.println("Enter a aiport location do not leave it empty");
		}
		
		if(Objects.nonNull(entity.getNoOfPlanes()) && entity.getNoOfPlanes()> 0) {
			flag = true;
		}else {
			System.out.println("Enter Aeroplanes do not leave it empty");
		}
		
		if(Objects.nonNull(entity.getNoOfRunways()) && entity.getNoOfRunways() > 0) {
			flag = true;
		}
		else {
			System.out.println("enter runways..Do not leave it empty");
		}
		
		return flag;
	}

}
