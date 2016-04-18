package com.alin.disertatie.bileteonline.model;

import java.io.Serializable;

/**
 * <p>
 * Bean class representing a price band entity.
 * </p>
 *
 * @author Alin Vasile
 * @version 1.0
 */
public class PriceBand implements Serializable {

	private static final long serialVersionUID = 7990683610594289444L;

	private long priceBand;
	
	private double price;
	
	private String currency;
	
	private long measureUnit;
	
	private long measureAmount;

	public long getPriceBand() {
		return priceBand;
	}

	public void setPriceBand(long priceBand) {
		this.priceBand = priceBand;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public long getMeasureUnit() {
		return measureUnit;
	}

	public void setMeasureUnit(long measureUnit) {
		this.measureUnit = measureUnit;
	}

	public long getMeasureAmount() {
		return measureAmount;
	}

	public void setMeasureAmount(long measureAmount) {
		this.measureAmount = measureAmount;
	}
	
}
