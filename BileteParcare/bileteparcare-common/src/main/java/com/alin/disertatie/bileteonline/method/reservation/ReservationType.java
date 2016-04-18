package com.alin.disertatie.bileteonline.method.reservation;

import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;
import java.util.Date;

/**
 * <p>
 * Class that creates a reservation by calling the pl/sql code.
 * </p>
 *
 * @author Alin Vasile
 * @version 1.0
 */
public class ReservationType implements SQLData {

	public static final String SQL_NAME = "OBJ_RESERVATION";
	
	private long reservationId;
	private long parking;
	private Date reservationDate;
	private Date reservationFrom;
	private Date reservationTo;
	private String vehicleNumber;
	private double chargedPrice;
	private String canceled;

	public String getSQLTypeName() throws SQLException {
		return SQL_NAME;
	}

	public void writeSQL(SQLOutput stream) throws SQLException {
		stream.writeLong(parking);
		stream.writeDate(reservationDate != null ? new java.sql.Date(
				reservationDate.getTime()) : null);
		stream.writeDate(reservationFrom != null ? new java.sql.Date(
				reservationFrom.getTime()) : null);
		stream.writeLong(reservationId);
		stream.writeDate(reservationTo != null ? new java.sql.Date(
				reservationTo.getTime()) : null);
		stream.writeString(vehicleNumber);
		stream.writeString(canceled);
	}
	
	public void readSQL(SQLInput stream, String typeName) throws SQLException {
		parking = stream.readLong();
		reservationDate = stream.readDate();
		reservationFrom = stream.readDate();
		reservationId = stream.readLong();
		reservationTo = stream.readDate();
		vehicleNumber = stream.readString();
		canceled = stream.readString();
	}


	public long getReservationId() {
		return reservationId;
	}

	public void setReservationId(long reservationId) {
		this.reservationId = reservationId;
	}

	public long getParking() {
		return parking;
	}

	public void setParking(long parking) {
		this.parking = parking;
	}

	public Date getReservationDate() {
		return reservationDate;
	}

	public void setReservationDate(Date reservationDate) {
		this.reservationDate = reservationDate;
	}

	public Date getReservationFrom() {
		return reservationFrom;
	}

	public void setReservationFrom(Date reservationFrom) {
		this.reservationFrom = reservationFrom;
	}

	public Date getReservationTo() {
		return reservationTo;
	}

	public void setReservationTo(Date reservationTo) {
		this.reservationTo = reservationTo;
	}

	public String getVehicleNumber() {
		return vehicleNumber;
	}

	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}

	public double getChargedPrice() {
		return chargedPrice;
	}

	public void setChargedPrice(double chargedPrice) {
		this.chargedPrice = chargedPrice;
	}

	public String getCanceled() {
		return canceled;
	}

	public void setCanceled(String canceled) {
		this.canceled = canceled;
	}

}
