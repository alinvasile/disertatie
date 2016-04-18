package com.alin.disertatie.bileteonline.model;

import java.io.Serializable;

/**
 * <p>
 * Bean class representing a tariff entity.
 * </p>
 *
 * @author Alin Vasile
 * @version 1.0
 */
public class Tarriff implements Serializable {

	private static final long serialVersionUID = 3302397667045358862L;

	private long tarriffId;
	
	private long priceBand;
	
	private String description;

	public long getTarriffId() {
		return tarriffId;
	}

	public void setTarriffId(long tarriffId) {
		this.tarriffId = tarriffId;
	}

	public long getPriceBand() {
		return priceBand;
	}

	public void setPriceBand(long priceBand) {
		this.priceBand = priceBand;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
