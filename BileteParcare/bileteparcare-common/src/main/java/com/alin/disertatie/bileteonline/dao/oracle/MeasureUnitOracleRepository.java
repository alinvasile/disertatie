package com.alin.disertatie.bileteonline.dao.oracle;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;

import com.alin.disertatie.bileteonline.dao.MeasureUnitRepository;
import com.alin.disertatie.bileteonline.model.MeasureUnit;

/**
 * <p>
 *   Oracle-based implementation repository interface handling the MeasureUnit data.
 * </p>
 *
 * @author Alin Vasile
 * @version 1.0
 */
public class MeasureUnitOracleRepository extends AbstractOracleRepository implements MeasureUnitRepository {

	public MeasureUnit add(MeasureUnit measureUnit) {
		long measureUnitId = getJdbcTemplate().queryForLong("SELECT SEQ_MEASURE_UNIT.nextval FROM DUAL");
		measureUnit.setMeasureUnitId(measureUnitId);
        getJdbcTemplate().update("INSERT INTO MEASURE_UNIT(MEASURE_UNIT_ID,MEASURE_UNIT_DESC) VALUES (?,?) ",
                new Object[]{measureUnit.getMeasureUnitId(),measureUnit.getMeasureUnitDesc()});
        return measureUnit;
	}

	public List<MeasureUnit> findAll() {
		return getSimpleJdbcTemplate().query("SELECT MEASURE_UNIT_ID,MEASURE_UNIT_DESC FROM MEASURE_UNIT",ParameterizedBeanPropertyRowMapper.newInstance(MeasureUnit.class));
	}

	public MeasureUnit findById(long id) {
		try{
    		return getSimpleJdbcTemplate().queryForObject("SELECT MEASURE_UNIT_ID,MEASURE_UNIT_DESC FROM MEASURE_UNIT WHERE MEASURE_UNIT_ID=?", ParameterizedBeanPropertyRowMapper.newInstance(MeasureUnit.class),id );
    	} catch(EmptyResultDataAccessException e){
    		return null;
    	}
	}

	public void update(MeasureUnit measureUnit) {
		getSimpleJdbcTemplate().update("UPDATE MEASURE_UNIT SET MEASURE_UNIT_DESC=? WHERE MEASURE_UNIT_ID = ? ",
				measureUnit.getMeasureUnitDesc(),measureUnit.getMeasureUnitId());
  
		
	}

}
