package com.alin.disertatie.bileteonline.ws;

import java.sql.Timestamp;
import java.util.Date;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import com.alin.disertatie.bileteonline.dao.oracle.OracleRepository;
import com.alin.disertatie.bileteonline.method.charging.Charge;
import com.alin.disertatie.bileteonline.method.charging.ComputePrice;
import com.alin.disertatie.bileteonline.method.exception.NoReservationFoundException;
import com.alin.disertatie.bileteonline.method.exception.PricingException;
import com.alin.disertatie.bileteonline.method.exception.ReservationExpiredException;
import com.alin.disertatie.bileteonline.method.reservation.CancelReservationByReservationNumber;
import com.alin.disertatie.bileteonline.method.reservation.CancelReservationByVehicleNumber;
import com.alin.disertatie.bileteonline.method.reservation.CheckReservationByReservationNumber;
import com.alin.disertatie.bileteonline.method.reservation.CheckReservationByVehicleNumber;
import com.alin.disertatie.bileteonline.method.reservation.ExtendReservation;
import com.alin.disertatie.bileteonline.method.reservation.MakeReservation;
import com.alin.disertatie.bileteonline.method.reservation.ReservationType;

@WebService(endpointInterface = "com.alin.disertatie.bileteonline.ws.BileteOnline")
@SOAPBinding(style=SOAPBinding.Style.RPC)
public class BileteOnlineImpl implements BileteOnline {

	protected static ApplicationContext ctx;
	protected static JdbcTemplate jdbcTemplate = null;
	
	static {
		ctx = new ClassPathXmlApplicationContext("appContext.xml");
		jdbcTemplate = getOracleRepository().getJdbcTemplate();
	}

	// cache the method beans to
	// ensure performance
	private CancelReservationByReservationNumber cancelByReservationNumber = new CancelReservationByReservationNumber(
			jdbcTemplate);

	private CancelReservationByVehicleNumber cancelByVehicleNumber = new CancelReservationByVehicleNumber(
			jdbcTemplate);

	private CheckReservationByReservationNumber checkByReservationNumber = new CheckReservationByReservationNumber(
			jdbcTemplate);

	private CheckReservationByVehicleNumber checkByVehicleNumber = new CheckReservationByVehicleNumber(
			jdbcTemplate);

	private MakeReservation makeReservation = new MakeReservation(jdbcTemplate);

	private ExtendReservation extendReservation = new ExtendReservation(
			jdbcTemplate);
	
	private ComputePrice computePrice = new ComputePrice(jdbcTemplate);
	
	private Charge charge = new Charge(jdbcTemplate);

		
	public void cancelReservationByReservationId(Long reservationId)
			throws ReservationExpiredException, NoReservationFoundException {
		cancelByReservationNumber.cancelReservation(reservationId);
	}

	
	public void cancelReservationByVehicleNumber(String vehicleNumber)
			throws ReservationExpiredException, NoReservationFoundException {
		cancelByVehicleNumber.cancelReservation(vehicleNumber);
	}

	
	public void checkReservationByReservationId(Long reservationId)
			throws ReservationExpiredException, NoReservationFoundException {
		checkByReservationNumber.checkReservation(reservationId);
	}

	
	public void checkReservationByVehicleNumber(String vehicleNumber)
			throws ReservationExpiredException, NoReservationFoundException {
		checkByVehicleNumber.checkReservation(vehicleNumber);
	}

	
	public ReservationType createReservation(ReservationType reservation) throws PricingException {
		double price = computePrice.computePrice(reservation.getReservationFrom(), reservation.getReservationTo());
		ReservationType createdReservation = makeReservation.execute(reservation);
		try {
			charge.charge(createdReservation.getReservationId(), price);
		} catch (PricingException e) {
			try {
				cancelByReservationNumber.cancelReservation(createdReservation.getReservationId());
			} catch(ReservationExpiredException ignore ){
				
			} catch(NoReservationFoundException ignore) {
				
			}
			throw e;
		}
		createdReservation.setChargedPrice(price);
		return createdReservation;
	}

	
	public double extendReservation(long reservationId, Date reservationTo)	throws NoReservationFoundException, PricingException {
		double price = computePrice.computePrice(new Date(), reservationTo);
		extendReservation.extendReservation(reservationId, reservationTo==null?null:new Timestamp(reservationTo.getTime()));
		charge.charge(reservationId, price);
		return price;
	}

	protected static OracleRepository getOracleRepository() {
		return (OracleRepository) ctx.getBean("repository");
	}

}
