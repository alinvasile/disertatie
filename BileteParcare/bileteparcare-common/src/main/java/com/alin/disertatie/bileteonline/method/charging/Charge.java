package com.alin.disertatie.bileteonline.method.charging;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import oracle.jdbc.OracleTypes;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;

import com.alin.disertatie.bileteonline.method.exception.PricingException;

public class Charge extends StoredProcedure {

	public Charge(JdbcTemplate jdbcTemplate) {
		super(jdbcTemplate, "PKG_CHARGING.FN_CHARGE");
		
		declareParameter(new SqlOutParameter("return",OracleTypes.VARCHAR));
		declareParameter(new SqlParameter("RESERVATION_ID",OracleTypes.NUMBER));
		declareParameter(new SqlParameter("PRICE",OracleTypes.NUMBER));
		
		setFunction(true);
			
		compile();
	}
	
	public void charge(long reservationId, double price) throws PricingException {
		Map<String,Number> params = new HashMap<String,Number>();
		
		params.put("RESERVATION_ID",reservationId);
		params.put("PRICE",price);
		
		Map results = execute(params);
        
        String result = (String)results.get("return");
        
        if(!"[SUCCESS]".equals(result)){
        	throw new PricingException(result);
        }
        
	}
	
}
