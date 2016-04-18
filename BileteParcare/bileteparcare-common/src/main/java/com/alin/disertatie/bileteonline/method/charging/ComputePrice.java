package com.alin.disertatie.bileteonline.method.charging;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import oracle.jdbc.OracleTypes;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;

public class ComputePrice extends StoredProcedure {

	public ComputePrice(JdbcTemplate jdbcTemplate) {
		super(jdbcTemplate, "PKG_CHARGING.FN_CALC_PRICE");
		
		declareParameter(new SqlOutParameter("return",OracleTypes.NUMBER));
		declareParameter(new SqlParameter("FROM_DATE",OracleTypes.TIMESTAMP));
		declareParameter(new SqlParameter("TO_DATE",OracleTypes.TIMESTAMP));
		
		setFunction(true);
			
		compile();
	}
	
	public double computePrice(Date from, Date to){
		if(from==null){
			throw new IllegalArgumentException("Reservation from cannot be null");
		}
		
		if(to==null){
			throw new IllegalArgumentException("Reservation to cannot be null");
		}
		
		Map<String,Date> params = new HashMap<String,Date>();
		
		params.put("FROM_DATE",from);
		params.put("TO_DATE",to);
		
		Map results = execute(params);
        
        Number result = (Number)results.get("return");
        
        return result.doubleValue();
	}
	
}
