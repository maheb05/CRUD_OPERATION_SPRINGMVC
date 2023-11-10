package com.tap.airport.dao;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tap.airport.entity.AirportEntity;


@Component("airportDaoImpl")
public class AirportDaoImpl implements AirportDAO {
	
	Scanner scan = new Scanner(System.in);
	
	@Autowired
	SessionFactory sessionFactory ;
	
	public AirportDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		System.out.println("session Factory invoked");
	}
	
	
	public void saveAirportEntity(AirportEntity entity) {
		
		Session session = null;
		
			try {
				session = sessionFactory.openSession();
				session.beginTransaction();
				
				session.save(entity);
				
				session.getTransaction().commit();
			}
			
			finally {
				if(session != null) {
					session.close();
					System.out.println("session closed");
				}
			}
		
		
	}

	public AirportEntity getAirportEntityById(int id) {
		
		AirportEntity entity = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			entity = session.get(AirportEntity.class, id);
			
			if(Objects.nonNull(entity)) {
				return entity;
			}
			else {
				System.out.println("enter valid id.. Id not found");
			}
		}
		
		finally {
			if(session != null) {
				session.close();
			}
		}
		return entity;
	}

	public Long getCountOfRunways() {
		
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();
			
			Query<Long> query = session.createQuery("select sum(ae.noOfRunways) from AirportEntity ae",Long.class);
			
			return query.getSingleResult();
		}
		finally {
			if(session != null) {
				session.close();
			}
		}
	}

	public void updateAirportEntity() {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();
			
			Query query = session.createQuery("update AirportEntity ae set ae.airportName = :name, ae.airportLocation = :location, ae.noOfPlanes = :planes , "
					+ "ae.noOfRunways = :runways where ae.airportId = :id");
			
			System.out.println("enter airport name");
			String arName = scan.nextLine();
			
			System.out.println("enter airport location");
			String arLocation = scan.nextLine();
			
			System.out.println("enter no of planes");
			int noofPlanes = scan.nextInt();
			
			System.out.println("enter no of runways");
			int noofRunways = scan.nextInt();
			
			System.out.println("Enter Airport Id");
			int aid = scan.nextInt();
			
			query.setParameter("name", arName);
			query.setParameter("location", arLocation);
			query.setParameter("planes", noofPlanes);
			query.setParameter("runways", noofRunways);
			query.setParameter("id", aid);
			
			int i = query.executeUpdate();
			
			if(i > 0) {
				System.out.println(i +" rows updated");
			}else {
				System.out.println("Something Went wrong ... Update unsuccessfull , please try again ");

			}
			transaction.commit();
		}
		finally {
			if(session != null) {
				session.close();
			}
		}
	}
	
	
	public void updateAirportByName(String name) {
		Session session = null;
		
		try {
			session = sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();
			
			Query query = session.createQuery("update AirportEntity ae set ae.airportLocation = :location, ae.noOfPlanes= :planes, ae.noOfRunways= :runways where ae.airportName=:name ");
			
			System.out.println("Enter location ");
			String newLocation = scan.nextLine();
			
			System.out.println("Enter No Of Planes ");
			int aeroplanes = scan.nextInt();
			
			System.out.println("Enter No Of Runways ");
			int noofRunways = scan.nextInt();
			
			query.setParameter("location", newLocation);
			query.setParameter("planes", aeroplanes);
			query.setParameter("runways", noofRunways);
			query.setParameter("name", name);
			
			int i = query.executeUpdate();
			
			if(i > 0) {
				System.out.println(i +" Rows updated Successfully ");
			}else {
				System.out.println("Something Went wrong ... Update unsuccessfull , please try again ");
			}
			transaction.commit();
		}
		finally {
			if(session != null) {
				session.close();
			}
		}
			
	}

	public void updateAirportByLocation(String location) {
		
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();
			
			Query query = session.createQuery("update AirportEntity ae set ae.airportName = :name , ae.noOfPlanes = :planes, ae.noOfRunways = :runways  where ae.airportLocation = :location");
			
			System.out.println("enter Name ");
			String newName = scan.nextLine();
			
			System.out.println("enter no of planes ");
			int noofPlanes = scan.nextInt();
			
			System.out.println("Enter no of runways ");
			int noofrunways = scan.nextInt();
			
			query.setParameter("name", newName);
			query.setParameter("planes", noofPlanes);
			query.setParameter("runways", noofrunways);
			query.setParameter("location", location);
			
			int i = query.executeUpdate();
			
			if(i>0) {
				System.out.println(i+" rows updated");
			}else {
				System.out.println("Something Went wrong ... Update unsuccessfull , please try again ");

			}
			
			transaction.commit();
			
		}
		finally {
			if(session != null) {
				session.close();
			}
		}
	}
	
	public void updateAirportById(int id) {
		Session session = null;
		
		try {
			session = sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();
			AirportEntity entity = session.get(AirportEntity.class, id);
			if(entity != null) {
				entity.setAirportName("HEllo");
				entity.setAirportLocation("hyderabad");
				entity.setNoOfPlanes(15);
				entity.setNoOfRunways(8);
				session.update(entity);
				System.out.println("Succesfully updated ");
			}
			else {
				System.out.println(" Id Not Found");
			}
			transaction.commit();
		}
		finally {
			
		}
	}

	public void deleteAirportById(int id) {
		
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();
			
			AirportEntity entity = session.get(AirportEntity.class, id);
			if(entity != null) {
				session.delete(entity);
				System.out.println("Successfully deleted");
			}
			else {
				System.out.println("ID not found ");
			}
			transaction.commit();
		}
		finally {
			if(session != null) {
				session.close();
			}
		}
	}

	public List<AirportEntity> getAllAirportentity() {
		
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		
		Query query = session.createQuery("from AirportEntity");
		List <AirportEntity> resultList = query.getResultList();
		
		LinkedList<AirportEntity> airportEntities = new LinkedList<AirportEntity>();
		
		for (AirportEntity entity : resultList) {
			airportEntities.add(entity);
		}
		transaction.commit(); 
		
		return airportEntities;
		
	}

	
}
