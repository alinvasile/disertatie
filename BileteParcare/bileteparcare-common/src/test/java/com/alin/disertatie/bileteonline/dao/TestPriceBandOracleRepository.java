package com.alin.disertatie.bileteonline.dao;

import java.util.List;

import org.junit.Test;

import com.alin.disertatie.bileteonline.model.MeasureUnit;
import com.alin.disertatie.bileteonline.model.PriceBand;

/**
 * <p>
 *   Test class for Oracle-based repository handling the PriceBand data.
 * </p>
 *
 * @author Alin Vasile
 * @version 1.0
 */

public class TestPriceBandOracleRepository extends AbstractTestOracleRepository{
	
	/**
	 * Test the measure unit oracle repository functionality.
	 */
	/*
	public void testMeasureUnitOracleRepository(){
		
		//insert the needed measure unit
		// to ensure that we have the
		// measure unit id = one
		MeasureUnit measureUnit = new MeasureUnit();
		measureUnit.setMeasureUnitDesc("WATT");
		
		measureUnit = getMeasureUnitRepository().add(measureUnit);
		
		PriceBand priceBand = new PriceBand();
		
		priceBand.setPriceBand(9999);
		priceBand.setPrice(1.76);
		priceBand.setCurrency("USD");
		priceBand.setMeasureUnit(measureUnit.getMeasureUnitId());
		priceBand.setMeasureAmount(1);
		
		priceBand = getPriceBandRepository().add(priceBand);
		
		PriceBand band = getPriceBandRepository().findById(priceBand.getPriceBand());
		assertEquals(priceBand.getPriceBand(),band.getPriceBand());
		assertEquals(priceBand.getPrice(),band.getPrice());
		assertEquals(priceBand.getCurrency(),band.getCurrency());
		assertEquals(priceBand.getMeasureAmount(),band.getMeasureAmount());
		assertEquals(priceBand.getMeasureUnit(),band.getMeasureUnit());
		
		priceBand.setCurrency("EUR");
		getPriceBandRepository().update(priceBand);
		
		band = getPriceBandRepository().findById(priceBand.getPriceBand());
		assertEquals("EUR",band.getCurrency());
		
		boolean found = false;
		List<PriceBand> all = getPriceBandRepository().findAll();
		for(PriceBand p : all){
			if(p.getPriceBand() == priceBand.getPriceBand()){
				found = true;
			}
		}
		
		assertTrue(found);
		
		PriceBand nullUnit = getPriceBandRepository().findById(-1);
		assertNull(nullUnit);		
		
	}
*/
	
	@Test
	public void testOk(){
		System.out.println("ok");
	}
	
}
