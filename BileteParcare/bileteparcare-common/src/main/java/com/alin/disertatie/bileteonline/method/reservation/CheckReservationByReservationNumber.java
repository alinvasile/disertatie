package com.alin.disertatie.bileteonline.method.reservation;

import java.util.HashMap;
import java.util.Map;

import oracle.jdbc.OracleTypes;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;

import com.alin.disertatie.bileteonline.method.exception.NoReservationFoundException;
import com.alin.disertatie.bileteonline.method.exception.ReservationExpiredException;

public class CheckReservationByReservationNumber extends StoredProcedure  {

	protected static final String VALID = "[VALID]";
	protected static final String INVALID = "INVALID";
	
	public CheckReservationByReservationNumber(JdbcTemplate jdbcTemplate) {
		super(jdbcTemplate, "PKG_RESERVATION.check_reservation");
		
		declareParameter(new SqlOutParameter("return",OracleTypes.CHAR));
		declareParameter(new SqlParameter("P_RESERVATION_ID",OracleTypes.NUMBER));
		
		setFunction(true);
			
		compile();
	}
	
	/**
	 * Checks if a vehicle has a valid reservation
	 * 
	 * @param vehicleNumber The vehicle's number
	 * 
	 * @throws ReservationExpiredException When the matching reservation is expired
	 * @throws NoReservationFoundException When the matching reservation was not found
	 */
	public void checkReservation(Long  reservationNumber) throws ReservationExpiredException, NoReservationFoundException{
	
		if(reservationNumber== null ){
			throw new IllegalArgumentException("Reservation number cannot be null");
		}
		
		Map<String,Object> params = new HashMap<String,Object>();
		
		params.put("P_RESERVATION_ID",reservationNumber);
				
        Map results = execute(params);
        
        String result = (String)results.get("return");
        System.out.println(result);
        if(!VALID.equals(result)){
        	String reason = result.substring(result.indexOf(",")+1, result.length()-1);
        	
        	System.out.println("Reason is : " + reason);
        	
        	if("Authorisation expired".equals(reason)){
        		throw new ReservationExpiredException(reason);
        	}
        	throw new NoReservationFoundException(reason);
        }
		
	}

}
