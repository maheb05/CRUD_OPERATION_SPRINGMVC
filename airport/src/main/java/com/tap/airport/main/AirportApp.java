package com.tap.airport.main;

import java.util.List;
import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tap.airport.dao.AirportDAO;
import com.tap.airport.dao.AirportDaoImpl;
import com.tap.airport.entity.AirportEntity;
import com.tap.airport.service.AirportEntityValidation;

public class AirportApp {
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		String location = "beanConfig.xml";
		ApplicationContext iocContainer = new ClassPathXmlApplicationContext(location);
		System.out.println(iocContainer);
		
		AirportDAO airportDao = iocContainer.getBean(AirportDAO.class);
		
		 //INSERTING DATA INTO TABLE
		
		AirportEntity entity = new AirportEntity("Nehru International", "Rajamandry", 20,10);
		
		AirportEntityValidation airportEntityValidation = iocContainer.getBean(AirportEntityValidation.class);
		boolean valid = airportEntityValidation.validateAirportEntity(entity);
		
		if(valid) {
			airportDao.saveAirportEntity(entity);
			System.out.println("Successfully Saved");
		}else {
			System.out.println("try gain later..");
		}
		
		
		///FETCHING DATA BY ID
		
		AirportEntity airportEntityById = airportDao.getAirportEntityById(2);
		System.out.println(airportEntityById);
		
		///UPDATE ENTITY AIRPORT BY ID	
		
		airportDao.updateAirportById(2);
		
		//UPDATE ENTITY BY AIRPORTNAME
		
		System.out.println("Enter Airport Name to Update the Record ");
		String arName = scan.nextLine();
		airportDao.updateAirportByName(arName);
		
		////UPDATE ENTITY BY AIRPORT LOCATION
		
		System.out.println("Enter Airport Location to update the Record ");
		String arLocation = scan.nextLine();
		airportDao.updateAirportByLocation(arLocation);
		
		///	UPDATING AIRPORT ENTITY
		
		airportDao.updateAirportEntity();
		
		// COUNT OF THE RUNWAYS
		
		System.out.println(airportDao.getCountOfRunways());;
		
		/////DELETE AIRPORT BY ID
		
		System.out.println("Enter Id to delete ");
		int id = scan.nextInt();
		airportDao.deleteAirportById(id);
		
		
		/// FETCHING ALL THE RECORDS FROM THE TABLE
		
		List<AirportEntity> allAirportentity = airportDao.getAllAirportentity();
		
		if(allAirportentity != null  && !allAirportentity.isEmpty()) {
			System.out.println("Here All the Records ");
			for (AirportEntity airportEntity : allAirportentity) {
				System.out.println(airportEntity);
			}
		}else {
			System.out.println("No records found");
		}
	
		ClassPathXmlApplicationContext ioc =(ClassPathXmlApplicationContext) iocContainer;
		ioc.close();
	}
}
