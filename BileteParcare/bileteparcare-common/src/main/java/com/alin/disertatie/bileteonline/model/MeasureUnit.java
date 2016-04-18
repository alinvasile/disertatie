package com.alin.disertatie.bileteonline.model;

import java.io.Serializable;

/**
 * <p>
 * Bean class representing a Measure Unit entity.
 * </p>
 *
 * @author Alin Vasile
 * @version 1.0
 */
public class MeasureUnit implements Serializable {

	private static final long serialVersionUID = -3351852569007150451L;

	private long measureUnitId;
	
	private String measureUnitDesc;

	public long getMeasureUnitId() {
		return measureUnitId;
	}

	public void setMeasureUnitId(long measureUnitId) {
		this.measureUnitId = measureUnitId;
	}

	public String getMeasureUnitDesc() {
		return measureUnitDesc;
	}

	public void setMeasureUnitDesc(String description) {
		this.measureUnitDesc = description;
	}
	
}
