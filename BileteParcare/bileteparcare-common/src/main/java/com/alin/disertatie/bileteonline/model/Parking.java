package com.alin.disertatie.bileteonline.model;

import java.io.Serializable;

/**
 * <p>
 * Bean class representing a Parking entity.
 * </p>
 *
 * @author Alin Vasile
 * @version 1.0
 */
public class Parking implements Serializable {
   
	private static final long serialVersionUID = -6800801481729457069L;

	private long parkingId;

    private String name;

    private String address;

    private String city;

    private String region;

    private String country;

    public long getParkingId() {
        return parkingId;
    }

    public void setParkingId(long parkingId) {
        this.parkingId = parkingId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }


}
