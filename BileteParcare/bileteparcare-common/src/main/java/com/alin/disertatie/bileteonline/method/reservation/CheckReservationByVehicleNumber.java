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

/**
 * <p>
 *  Checks a reservation by calling the pl/sql code.
 * </p>
 *
 * @author Alin Vasile
 * @version 1.0
 */
public class CheckReservationByVehicleNumber  extends StoredProcedure  {

	protected static final String VALID = "[VALID]";
	protected static final String INVALID = "INVALID";
	
	public CheckReservationByVehicleNumber(JdbcTemplate jdbcTemplate) {
		super(jdbcTemplate, "PKG_RESERVATION.check_reservation");
		
		declareParameter(new SqlOutParameter("return",OracleTypes.CHAR));
		declareParameter(new SqlParameter("P_VEHICLE_NUMBER",OracleTypes.VARCHAR));
		
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
	public void checkReservation(String vehicleNumber) throws ReservationExpiredException, NoReservationFoundException{
	
		if(vehicleNumber== null || "".equals(vehicleNumber.trim())){
			throw new IllegalArgumentException("Vehicle number cannot be null");
		}
		
		Map<String,Object> params = new HashMap<String,Object>();
		
		params.put("P_VEHICLE_NUMBER",vehicleNumber);
				
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
