package com.alin.disertatie.bileteonline.method.reservation;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import oracle.jdbc.OracleTypes;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;

import com.alin.disertatie.bileteonline.method.exception.NoReservationFoundException;

/**
 * <p>
 *  Extends a reservation by calling the pl/sql code.
 * </p>
 *
 * @author Alin Vasile
 * @version 1.0
 */
public class ExtendReservation extends StoredProcedure {

	protected static final String SUCCESS = "[SUCCESS]";
	protected static final String FAILED = "FAILED";
	
	public ExtendReservation(JdbcTemplate jdbcTemplate) {
		super(jdbcTemplate, "PKG_RESERVATION.extend_reservation");
		
		declareParameter(new SqlOutParameter("return",OracleTypes.CHAR));
		declareParameter(new SqlParameter("P_RESERVATION_ID",OracleTypes.NUMBER));
		declareParameter(new SqlParameter("P_RESERVATION_TO",OracleTypes.TIMESTAMP));
		
		setFunction(true);
			
		compile();
	}
	
	/**
	 * Extends a reservation for a vehicle
	 * 
	 * @param testItem
	 * @return
	 */
	public void extendReservation(final Number reservationId, Timestamp reservationTo) throws NoReservationFoundException {
		
		if(reservationTo==null){
			throw new IllegalArgumentException("Reservation to cannot be null");
		}
		
		Map<String,Object> params = new HashMap<String,Object>();
		
		params.put("P_RESERVATION_ID",reservationId);
		params.put("P_RESERVATION_TO",reservationTo);
				
        Map results = execute(params);
           
        String result = (String)results.get("return");
        System.out.println(result);
        if(!SUCCESS.equals(result)){
        	throw new NoReservationFoundException( result.substring(result.indexOf(",")+1, result.length()-1));
        }
                
	}
}
