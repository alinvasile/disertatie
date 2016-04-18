package com.alin.disertatie.bileteonline.method.reservation;

import java.sql.Timestamp;
import java.util.Calendar;

import junit.framework.TestCase;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alin.disertatie.bileteonline.dao.oracle.OracleRepository;
import com.alin.disertatie.bileteonline.method.exception.NoReservationFoundException;

/**
 * Tests the ExtendReservation operation
 * 
 * @author Vasile Alin
 *
 */
public class TestExtendReservation extends TestCase {

	protected static ApplicationContext ctx;

	static{
		ctx = new ClassPathXmlApplicationContext("appContext.xml");
		assertNotNull(ctx);          
	}
	
	/*@Test
	public void testExtendReservation(){
		ReservationType reservation = new ReservationType();
		
		Calendar cal = Calendar.getInstance();
		reservation.setReservationFrom(cal.getTime());
		
		cal.add(Calendar.HOUR,1); // 1 hour
		
		reservation.setReservationTo(cal.getTime()); 
		reservation.setVehicleNumber("GL-07-APX");
		reservation.setParking(1);
		
		MakeReservation makeReservation = new MakeReservation(getOracleRepository().getJdbcTemplate() );
		reservation = makeReservation.execute(reservation);
		
		System.out.println(reservation.getReservationId());
		System.out.println(reservation.getReservationDate());
		
		ExtendReservation ext = new ExtendReservation(getOracleRepository().getJdbcTemplate());
		try {
			ext.extendReservation(reservation.getReservationId(), new Timestamp(reservation.getReservationDate().getTime() + 1000 * 60 *60 )); //one hour in plus
		} catch (NoReservationFoundException e) {
			e.printStackTrace();
			fail(e.getMessage());
		} 
	}*/
	
	@Test
	public void testInvalidRequestId(){
		ExtendReservation ext = new ExtendReservation(getOracleRepository().getJdbcTemplate());
		try {
			ext.extendReservation(-64564, new Timestamp(System.currentTimeMillis() )); 
			fail("testInvalidRequestId should fail");
		} catch (NoReservationFoundException e) {
			// ok
		} 
	}
	
	/**
	 * @return A Spring-configured MeasureUnitRepository bean instance 
	 */
	protected OracleRepository getOracleRepository(){
		return (OracleRepository) ctx.getBean("repository");
	}
	
}
