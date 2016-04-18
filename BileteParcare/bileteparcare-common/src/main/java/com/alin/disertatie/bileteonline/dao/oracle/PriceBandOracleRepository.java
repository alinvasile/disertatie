package com.alin.disertatie.bileteonline.dao.oracle;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;

import com.alin.disertatie.bileteonline.dao.PriceBandRepository;
import com.alin.disertatie.bileteonline.model.PriceBand;

/**
 * <p>
 * Oracle-based implementation repository interface handling the MesureUnit
 * data.
 * </p>
 * 
 * @author Alin Vasile
 * @version 1.0
 */
public class PriceBandOracleRepository extends AbstractOracleRepository
		implements PriceBandRepository {

	public PriceBand add(PriceBand priceBand) {
		getJdbcTemplate()
				.update(
						"INSERT INTO PRICE_BAND( PRICE_BAND,PRICE,CURRENCY,MEASURE_UNIT,MEASURE_AMOUNT ) VALUES (?,?,?,?,?) ",
						new Object[] { priceBand.getPriceBand(),
								priceBand.getPrice(), priceBand.getCurrency(),
								priceBand.getMeasureUnit(),
								priceBand.getMeasureAmount() });
		return priceBand;
	}

	public List<PriceBand> findAll() {
		return getSimpleJdbcTemplate()
				.query(
						"SELECT PRICE_BAND,PRICE,CURRENCY,MEASURE_UNIT,MEASURE_AMOUNT FROM PRICE_BAND",
						ParameterizedBeanPropertyRowMapper
								.newInstance(PriceBand.class));
	}

	public PriceBand findById(long priceBand) {
		try {
			return getSimpleJdbcTemplate()
					.queryForObject(
							"SELECT PRICE_BAND,PRICE,CURRENCY,MEASURE_UNIT,MEASURE_AMOUNT FROM PRICE_BAND WHERE PRICE_BAND=?",
							ParameterizedBeanPropertyRowMapper
									.newInstance(PriceBand.class), priceBand);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public void update(PriceBand priceBand) {
		getSimpleJdbcTemplate()
				.update(
						"UPDATE PRICE_BAND SET PRICE=?,CURRENCY=?,MEASURE_UNIT=?,MEASURE_AMOUNT=? WHERE PRICE_BAND = ? ",
						priceBand.getPrice(), priceBand.getCurrency(),
						priceBand.getMeasureUnit(),
						priceBand.getMeasureAmount(), priceBand.getPriceBand());
	}

}
