package com.alin.disertatie.bileteonline.method.exception;

/**
 * 
 * Exception thrown in cases when a matching reservation was not found for provided details.
 * 
 * @author Vasile Alin
 *
 */
public class NoReservationFoundException extends Exception{

	private static final long serialVersionUID = 1L;

	public NoReservationFoundException() {
		super();
	}

	public NoReservationFoundException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public NoReservationFoundException(String arg0) {
		super(arg0);
	}

	public NoReservationFoundException(Throwable arg0) {
		super(arg0);
	}

}
