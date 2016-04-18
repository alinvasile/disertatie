package com.alin.disertatie.bileteonline.ws;

import java.util.Date;

import javax.jws.WebParam;
import javax.jws.WebService;

import com.alin.disertatie.bileteonline.method.exception.NoReservationFoundException;
import com.alin.disertatie.bileteonline.method.exception.PricingException;
import com.alin.disertatie.bileteonline.method.exception.ReservationExpiredException;
import com.alin.disertatie.bileteonline.method.reservation.ReservationType;

/**
 * <p>
 *   Web-service interface for the exposed tickets operations.
 * </p>
 *
 * @author Alin Vasile
 * @version 1.0
 */
@WebService
public interface BileteOnline {

	/**
	 * Creates a reservation for a vehicle.
	 * 
	 * @param reservation The reservation parameters.May not be null
	 * @return A reservation object populated with the reservation id.
	 * @throws PricingException 
	 * @throws NoReservationFoundException 
	 * @throws ReservationExpiredException 
	 */
	ReservationType createReservation( 
			@WebParam(name="reservation") final ReservationType reservation) throws PricingException, ReservationExpiredException, NoReservationFoundException ;
	
	/**
	 * Extends an existing reservation for a given period of time.
	 * 
	 * @param reservationId The reservation identified, to be extended.
	 * @param reservationTo The To date to be extended.
	 * 
	 * @return The charged price.
	 * 
	 * @throws NoReservationFoundException if the targeted to extend reservation is expired or not present.
	 * @throws PricingException 
	 */
	double extendReservation(
			@WebParam(name="reservationId") final long reservationId, 
			@WebParam(name="reservationTo") final Date reservationTo)
			throws NoReservationFoundException, PricingException;
	
	/**
	 * Checks if a given reservationId corresponds to a valid reservation.
	 * 
	 * @param reservationId
	 * @throws ReservationExpiredException If the reservation is expired.
	 * @throws NoReservationFoundException If the reservation is not in the repository.
	 */
	void checkReservationByReservationId(
			@WebParam(name="reservationId") Long reservationId) throws ReservationExpiredException, NoReservationFoundException;
	
	/**
	 * Checks if a given vehicle number has a valid reservation.
	 * 
	 * @param vehicleNumber
	 * @throws ReservationExpiredException If the reservation is expired.
	 * @throws NoReservationFoundException If the reservation is not in the repository.
	 */
	void checkReservationByVehicleNumber(
			@WebParam(name="vehicleNumber") String vehicleNumber) throws ReservationExpiredException, NoReservationFoundException;
	
	/**
	 * Cancels a reservation, by the reservationId.
	 * 
	 * @param reservationId
	 * @throws ReservationExpiredException
	 * @throws NoReservationFoundException
	 */
	void cancelReservationByReservationId(
			@WebParam(name="reservationId") Long reservationId) throws ReservationExpiredException, NoReservationFoundException;
	
	/**
	 * Cancels a reservation, by the vehicle number.
	 * 
	 * @param reservationId
	 * @throws ReservationExpiredException
	 * @throws NoReservationFoundException
	 */
	void cancelReservationByVehicleNumber(
			@WebParam(name="vehicleNumber") String vehicleNumber) throws ReservationExpiredException, NoReservationFoundException;
		
		
}
