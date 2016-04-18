package com.alin.disertatie.bileteonline.dao;

import java.util.List;

import org.junit.Test;

import com.alin.disertatie.bileteonline.model.Parking;

/**
 * <p>
 *   Test class for Oracle-based repository handling the Parking data.
 * </p>
 *
 * @author Alin Vasile
 * @version 1.0
 */
public class TestParkingOracleRepository extends AbstractTestOracleRepository {

	/**
	 * Test the parking oracle repository functionality.
	 */
	@Test
	public void testParkingOracleRepository(){
		Parking parking = new Parking();

		parking.setName("TEST_PARKING");
		parking.setAddress("ROMANCIERILOR 1");
		parking.setCity("GALATI");
		parking.setRegion("MOLDOVA");
		parking.setCountry("ROMANIA");

		parking = getParkingRepository().add(parking);
		assertTrue(parking.getParkingId() > 0);

		Parking copiedParking = getParkingRepository().findById(parking.getParkingId());
		assertEquals(copiedParking.getParkingId(), parking.getParkingId());
		assertEquals(parking.getName(),copiedParking.getName());
		assertEquals(parking.getAddress(),copiedParking.getAddress());
		assertEquals(parking.getCity(),copiedParking.getCity());
		assertEquals(parking.getRegion(),copiedParking.getRegion());
		assertEquals(parking.getCountry(),copiedParking.getCountry());

		copiedParking.setCountry("IRELAND");
		copiedParking.setAddress("Zorileanu 5");
		copiedParking.setCity("BUCHAREST");
		copiedParking.setName("PARKING-GH1");
		copiedParking.setRegion("MUNTENIA");
		getParkingRepository().update(copiedParking);

		copiedParking = getParkingRepository().findById(copiedParking.getParkingId());

		assertEquals("BUCHAREST",copiedParking.getCity());
		assertEquals("Zorileanu 5",copiedParking.getAddress());
		assertEquals("IRELAND",copiedParking.getCountry());
		assertEquals("PARKING-GH1",copiedParking.getName());
		assertEquals("MUNTENIA",copiedParking.getRegion());

		List<Parking> parkingList = getParkingRepository().findAll();
		boolean found = false;
		for(Parking p : parkingList){
			if(p.getParkingId()==copiedParking.getParkingId()){
				found = true;
			}
		}

		assertTrue(found);

		Parking parkingNull = getParkingRepository().findById(-1);
		assertNull(parkingNull);
	}



}
