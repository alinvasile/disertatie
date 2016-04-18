package com.alin.disertatie.bileteonline.dao.oracle;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;

import com.alin.disertatie.bileteonline.dao.TariffRepository;
import com.alin.disertatie.bileteonline.model.Tarriff;

/**
 * <p>
 *   Oracle-based implementation repository interface handling the Tariff data.
 * </p>
 *
 * @author Alin Vasile
 * @version 1.0
 */
public class TariffOracleRepository extends AbstractOracleRepository implements TariffRepository {

	public Tarriff add(Tarriff tariff) {
		long tariffId = getJdbcTemplate().queryForLong("SELECT SEQ_Tarriff.nextval FROM DUAL");
		tariff.setTarriffId(tariffId);
		
        getJdbcTemplate().update("INSERT INTO TARRIFF(TARRIFF_ID,PRICE_BAND,DESCRIPTION) VALUES (?,?,?) ",
                new Object[]{tariff.getTarriffId(),tariff.getPriceBand(),tariff.getDescription()});
        return tariff;
	}

	public List<Tarriff> findAll() {
		return getSimpleJdbcTemplate().query("SELECT TARRIFF_ID,PRICE_BAND,DESCRIPTION FROM TARRIFF",ParameterizedBeanPropertyRowMapper.newInstance(Tarriff.class));

	}

	public Tarriff findById(long tariffId) {
		try{
    		return getSimpleJdbcTemplate().queryForObject("SELECT TARRIFF_ID,PRICE_BAND,DESCRIPTION FROM TARRIFF WHERE TARRIFF_ID=?", ParameterizedBeanPropertyRowMapper.newInstance(Tarriff.class),tariffId );
    	} catch(EmptyResultDataAccessException e){
    		return null;
    	}
	}

	public void update(Tarriff tariff) {
		getSimpleJdbcTemplate().update("UPDATE TARRIFF SET PRICE_BAND=?,DESCRIPTION=? WHERE TARRIFF_ID = ? ",
				tariff.getPriceBand(),tariff.getDescription(),tariff.getTarriffId());
  
	}

}
