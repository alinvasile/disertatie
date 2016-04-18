package com.alin.disertatie.bileteonline.method.exception;

/**
 * 
 * Exception thrown in cases when an expired reservation is found for provided details.
 * 
 * @author Vasile Alin
 *
 */
public class ReservationExpiredException extends Exception {

	private static final long serialVersionUID = 1L;

	public ReservationExpiredException() {
		super();
	}

	public ReservationExpiredException(String message, Throwable cause) {
		super(message, cause);
	}

	public ReservationExpiredException(String message) {
		super(message);
	}

	public ReservationExpiredException(Throwable cause) {
		super(cause);
	}

}
