package com.alin.disertatie.bileteonline.method.reservation;

import java.util.Calendar;

import junit.framework.TestCase;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alin.disertatie.bileteonline.dao.oracle.OracleRepository;
import com.alin.disertatie.bileteonline.method.exception.NoReservationFoundException;
import com.alin.disertatie.bileteonline.method.exception.ReservationExpiredException;

/**
 * Tests the CheckReservation operation
 * 
 * @author Vasile Alin
 *
 */
public class TestCheckReservationByvehicleNumber extends TestCase {

	protected static ApplicationContext ctx;

	static{
		ctx = new ClassPathXmlApplicationContext("appContext.xml");
		assertNotNull(ctx);          
	}
	
	MakeReservation makeReservation = new MakeReservation(getOracleRepository().getJdbcTemplate() );
	CheckReservationByVehicleNumber chkReservation = new CheckReservationByVehicleNumber(getOracleRepository().getJdbcTemplate());
	
	@Test
	public void testEmpty(){
		
	}
	
	/*@Test
	public void testCheckReservation(){
		ReservationType reservation = new ReservationType();
		
		Calendar cal = Calendar.getInstance();
		reservation.setReservationFrom(cal.getTime());
		
		cal.add(Calendar.DAY_OF_MONTH,1); // 1 hour
		
		reservation.setReservationTo(cal.getTime()); 
		reservation.setVehicleNumber("GL-10-BZH");
		reservation.setParking(1);
		
		
		reservation = makeReservation.execute(reservation);
		
		CheckReservationByVehicleNumber chkReservation = new CheckReservationByVehicleNumber(getOracleRepository().getJdbcTemplate());
		try {
			chkReservation.checkReservation("GL-10-BZH");
		} catch (ReservationExpiredException e) {
			e.printStackTrace();
			fail(e.getMessage());
		} catch (NoReservationFoundException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
		
	}

	@Test
	public void testInexistentCheckReservation() {
		try {
			chkReservation.checkReservation("GL-00-IUU");
			fail("Not existing number should fail!");
		} catch (ReservationExpiredException e) {
			fail("Not existing number should not throw ReservationExpiredException!");
		} catch (NoReservationFoundException e) {
		}
	}*/
	
	/*@Test
	public void testExpiredCheckReservation(){
		ReservationType reservation = new ReservationType();
		
		Calendar cal = Calendar.getInstance();
		reservation.setReservationFrom(cal.getTime());
		
		reservation.setReservationTo(cal.getTime()); 
		reservation.setVehicleNumber("GL-80-BZH");
		reservation.setParking(1);
		
		reservation = makeReservation.execute(reservation);
		
		
		try {
			chkReservation.checkReservation("GL-80-BZH");
			fail("Expired reservation situation. Should fail");
		} catch (ReservationExpiredException e) {
		} catch (NoReservationFoundException e) {
			e.printStackTrace();
			fail("Expired reservation situation should not throw an NoReservationFoundException");
		}
		
	}*/
	
	/**
	 * @return A Spring-configured MeasureUnitRepository bean instance 
	 */
	protected OracleRepository getOracleRepository(){
		return (OracleRepository) ctx.getBean("repository");
	}
	
}
