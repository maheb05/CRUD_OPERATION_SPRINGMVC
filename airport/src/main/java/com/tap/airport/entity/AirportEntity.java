package com.tap.airport.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "airport_table")
public class AirportEntity {
	
	
	@Column(name = "AIRPORT_ID")
	@Id
	private int airportId;
	
	@Column(name = "AIRPORT_NAME")
	private String airportName;
	
	@Column(name = "AIRPORT_LOCATION")
	private String airportLocation;
	
	@Column(name = "NO_OF_PLANES")
	private int noOfPlanes;
	
	@Column(name = "NO_OF_RUNWAYS")
	private int noOfRunways;
	
	public AirportEntity() {
		
	}
	public AirportEntity(String airportName, String airportLocation, int noOfPlanes, int noOfRunways) {
		super();
		this.airportName = airportName;
		this.airportLocation = airportLocation;
		this.noOfPlanes = noOfPlanes;
		this.noOfRunways = noOfRunways;
	}

	public int getAirportId() {
		return airportId;
	}

	public String getAirportName() {
		return airportName;
	}

	public void setAirportName(String airportName) {
		this.airportName = airportName;
	}

	public String getAirportLocation() {
		return airportLocation;
	}

	public void setAirportLocation(String airportLocation) {
		this.airportLocation = airportLocation;
	}

	public int getNoOfPlanes() {
		return noOfPlanes;
	}

	public void setNoOfPlanes(int noOfPlanes) {
		this.noOfPlanes = noOfPlanes;
	}

	public int getNoOfRunways() {
		return noOfRunways;
	}

	public void setNoOfRunways(int noOfRunways) {
		this.noOfRunways = noOfRunways;
	}
	
	@Override
	public String toString() {
		return getAirportId()+" "+getAirportName()+" "+getAirportLocation()+" "+getNoOfPlanes()+" "+getNoOfRunways();
	}
	
}
