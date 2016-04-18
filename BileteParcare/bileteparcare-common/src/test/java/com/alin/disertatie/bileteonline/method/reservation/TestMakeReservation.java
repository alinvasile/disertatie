package com.alin.disertatie.bileteonline.method.reservation;

import java.util.Calendar;

import junit.framework.TestCase;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alin.disertatie.bileteonline.dao.oracle.OracleRepository;

/**
 * Tests the CreateReservation operation
 * 
 * @author Vasile Alin
 *
 */
public class TestMakeReservation extends TestCase {
	
	protected static ApplicationContext ctx;

	static{
		ctx = new ClassPathXmlApplicationContext("appContext.xml");
		assertNotNull(ctx);          
	}
	
	@Test
	public void testEmpty(){
		
	}
	
	@Test
	public void testMakeReservation(){
		ReservationType reservation = new ReservationType();
		
		Calendar cal = Calendar.getInstance();
		reservation.setReservationFrom(cal.getTime());
		
		cal.add(Calendar.HOUR,1); // 1 hour
		
		reservation.setReservationTo(cal.getTime()); 
		reservation.setVehicleNumber("GL-06-APX");
		reservation.setParking(1);
		
		MakeReservation makeReservation = new MakeReservation(getOracleRepository().getJdbcTemplate() );
		reservation = makeReservation.execute(reservation);
		
		System.out.println(reservation.getReservationId());
		System.out.println(reservation.getReservationDate());
	}
	
	/**
	 * @return A Spring-configured MeasureUnitRepository bean instance 
	 */
	protected OracleRepository getOracleRepository(){
		return (OracleRepository) ctx.getBean("repository");
	}
	
}
