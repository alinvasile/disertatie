package com.alin.disertatie.bileteonline.dao;

import java.util.List;

import org.junit.Test;

import com.alin.disertatie.bileteonline.model.MeasureUnit;

/**
 * <p>
 *   Test class for Oracle-based repository handling the MeasureUnit data.
 * </p>
 *
 * @author Alin Vasile
 * @version 1.0
 */
public class TestMeasureUnitOracleRepository extends AbstractTestOracleRepository {

	
	/**
	 * Test the measure unit oracle repository functionality.
	 */
	@Test
	public void testMeasureUnitOracleRepository(){
		
		MeasureUnit measureUnit = new MeasureUnit();
		measureUnit.setMeasureUnitDesc("WATT");
		
		getMeasureUnitRepository().add(measureUnit);
		assertTrue(measureUnit.getMeasureUnitId()>0);
		
		MeasureUnit unit = getMeasureUnitRepository().findById(measureUnit.getMeasureUnitId());
		assertTrue(unit.getMeasureUnitId()==measureUnit.getMeasureUnitId());
		assertEquals(unit.getMeasureUnitDesc(),measureUnit.getMeasureUnitDesc());
		
		unit.setMeasureUnitDesc("KILOVOLT");
		getMeasureUnitRepository().update(unit);
		
		unit = getMeasureUnitRepository().findById(unit.getMeasureUnitId());
		assertEquals("KILOVOLT",unit.getMeasureUnitDesc());
		
		boolean found = false;
		List<MeasureUnit> measureUnitList = getMeasureUnitRepository().findAll();
		for(MeasureUnit m: measureUnitList){
			if(m.getMeasureUnitId() == unit.getMeasureUnitId()){
				found = true;
			}
		}
		
		assertTrue(found);
		
		MeasureUnit nullUnit = getMeasureUnitRepository().findById(-1);
		assertNull(nullUnit);
	}
	
}
