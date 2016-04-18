package com.alin.disertatie.bileteonline.model;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * Bean class representing a campaign entity.
 * </p>
 *
 * @author Alin Vasile
 * @version 1.0
 */
public class Campaign implements Serializable{
	
	private static final long serialVersionUID = 3923891499176757455L;

	private long campaignId;
	
	private String campaignName;
	
	private Date campaignFrom;
	
	private Date campaignTo;
	
	private String details;

	public long getCampaignId() {
		return campaignId;
	}

	public void setCampaignId(long campaignId) {
		this.campaignId = campaignId;
	}

	public String getCampaignName() {
		return campaignName;
	}

	public void setCampaignName(String campaignName) {
		this.campaignName = campaignName;
	}

	public Date getCampaignFrom() {
		return campaignFrom;
	}

	public void setCampaignFrom(Date from) {
		this.campaignFrom = from;
	}

	public Date getCampaignTo() {
		return campaignTo;
	}

	public void setCampaignTo(Date campaignTo) {
		this.campaignTo = campaignTo;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

}
