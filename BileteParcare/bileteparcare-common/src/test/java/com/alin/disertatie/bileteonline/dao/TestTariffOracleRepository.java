package com.alin.disertatie.bileteonline.dao;

import java.util.List;

import org.junit.Test;

import com.alin.disertatie.bileteonline.model.MeasureUnit;
import com.alin.disertatie.bileteonline.model.PriceBand;
import com.alin.disertatie.bileteonline.model.Tarriff;

public class TestTariffOracleRepository extends AbstractTestOracleRepository {

	/**
	 * Test the tariff oracle repository functionality.
	 */
	/*
	public void testPriceBandRepository(){
		
		insertNeededPriceBand();
		
		Tarriff tariff = new Tarriff();
		
		tariff.setPriceBand(1234);
		tariff.setDescription("DAILY REGULAR TARIFF");
		
		Tarriff insertedTariff = getTariffRepository().add(tariff);
		assertTrue(insertedTariff.getTarriffId() > 0);
		
		Tarriff foundTarriff = getTariffRepository().findById(insertedTariff.getTarriffId());
		assertEquals(tariff.getPriceBand(),foundTarriff.getPriceBand());
		assertEquals(tariff.getDescription(),foundTarriff.getDescription());
		
		tariff.setDescription("DAILY REGULAR WEEKEND TARIFF");
		getTariffRepository().update(tariff);
		
		foundTarriff = getTariffRepository().findById(insertedTariff.getTarriffId());
		
		assertEquals(1234,foundTarriff.getPriceBand());
		assertEquals("DAILY REGULAR WEEKEND TARIFF",foundTarriff.getDescription());
		
		boolean found = false;
		List<Tarriff> tarriffList = getTariffRepository().findAll();
		for(Tarriff m: tarriffList){
			if(m.getTarriffId() == insertedTariff.getTarriffId()){
				found = true;
			}
		}
		
		assertTrue(found);
		
		Tarriff nullUnit = getTariffRepository().findById(-1);
		assertNull(nullUnit);
	}*/

	@SuppressWarnings(value="unchecked")
	private void insertNeededPriceBand() {
		
		//insert the needed measure unit
		// to ensure that we have the
		// measure unit id = one
		MeasureUnit measureUnit = new MeasureUnit();
		measureUnit.setMeasureUnitDesc("WATT");
		
		measureUnit = getMeasureUnitRepository().add(measureUnit);
		
		PriceBand priceBand = new PriceBand();
		
		priceBand.setPriceBand(1234);
		priceBand.setPrice(1.76);
		priceBand.setCurrency("USD");
		priceBand.setMeasureUnit(measureUnit.getMeasureUnitId());
		priceBand.setMeasureAmount(1);
		
		priceBand = getPriceBandRepository().add(priceBand);
	}
	
	@Test
	public void testOk(){
		System.out.println("ok");		
	}
	
	
}
