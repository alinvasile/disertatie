package com.alin.disertatie.bileteonline.dao;

import com.alin.disertatie.bileteonline.model.Parking;

import java.util.List;

/**
 * <p>
 *   Repository interface handling the Parking data.
 * </p>
 *
 * @author Alin Vasile
 * @version 1.0
 */
public interface ParkingRepository {

    /**
     * Adds a new Parking instance to the repository
     * @param parking The parking to be added
     * @return the updated Parking instance having the parkingId attribute with the new value 
     */
    Parking add(Parking parking);

    /**
     * Searches for a Parking in the repository, by a given parking id.
     * @param id The id of the Parking being searched
     * @return The matching Parking instance or null if not found
     */
    Parking findById(long id);

    /**
     * Updates an existing Parking in the repository.
     * @param parking The instance to update the repository
     */
    void update(Parking parking);

    /**
     * Returns all the Parking instances existing in the repository.
     * @return  a List containing the Parkings in the repository. The list will be empty if no Parking instances are in the repository
     */
    List<Parking> findAll();

}
