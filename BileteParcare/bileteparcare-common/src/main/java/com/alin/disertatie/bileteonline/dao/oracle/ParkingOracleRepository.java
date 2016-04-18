package com.alin.disertatie.bileteonline.dao.oracle;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;

import com.alin.disertatie.bileteonline.dao.ParkingRepository;
import com.alin.disertatie.bileteonline.model.Parking;

/**
 * <p>
 *   Oracle-based implementation repository interface handling the Parking data.
 * </p>
 *
 * @author Alin Vasile
 * @version 1.0
 */
public class ParkingOracleRepository extends AbstractOracleRepository implements ParkingRepository {

    public Parking add(Parking parking){
        long parkingId = getJdbcTemplate().queryForLong("SELECT SEQ_PARKING.nextval FROM DUAL");
        parking.setParkingId(parkingId);
        getJdbcTemplate().update("INSERT INTO PARKING(PARKING_ID,NAME,ADDRESS,CITY,REGION,COUNTRY) VALUES (?,?,?,?,?,?) ",
                new Object[]{parking.getParkingId(),parking.getName(),parking.getAddress(),parking.getCity(),parking.getRegion(),parking.getCountry()});
        return parking;
    }

    public Parking findById(long id) {
    	try{
    		return getSimpleJdbcTemplate().queryForObject("SELECT PARKING_ID,NAME,ADDRESS,CITY,REGION,COUNTRY FROM PARKING WHERE PARKING_ID=?", ParameterizedBeanPropertyRowMapper.newInstance(Parking.class),id );
    	} catch(EmptyResultDataAccessException e){
    		return null;
    	}
    }

    public void update(Parking parking) {
        getSimpleJdbcTemplate().update("UPDATE PARKING SET NAME=?,ADDRESS=?,CITY=?,REGION=?,COUNTRY=? WHERE PARKING_ID = ? ",
                parking.getName(),parking.getAddress(),parking.getCity(),parking.getRegion(),parking.getCountry(),parking.getParkingId());
    }

    public List<Parking> findAll() {
        return getSimpleJdbcTemplate().query("SELECT PARKING_ID,NAME,ADDRESS,CITY,REGION,COUNTRY FROM PARKING",ParameterizedBeanPropertyRowMapper.newInstance(Parking.class));
    }

}
