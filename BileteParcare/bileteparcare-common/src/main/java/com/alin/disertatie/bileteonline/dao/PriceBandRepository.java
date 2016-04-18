package com.alin.disertatie.bileteonline.dao;

import java.util.List;

import com.alin.disertatie.bileteonline.model.PriceBand;

/**
 * <p>
 *   Repository interface handling the price band data.
 * </p>
 *
 * @author Alin Vasile
 * @version 1.0
 */
public interface PriceBandRepository {

	/**
     * Adds a new PriceBand instance to the repository
     * @param priceBand The price band to be added
     * @return the same PriceBand instance that was stored in the database  
     */
	PriceBand add(PriceBand priceBand);
	
	/**
     * Searches for a PriceBand in the repository, by a given price band number.
     * @param priceBand The priceBand number being searched
     * @return The matching PriceBand instance or null if not found
     */
	PriceBand findById(long priceBand);
	
	/**
     * Updates an existing price band in the repository.
     * @param priceBand The instance to update the repository
     */
    void update(PriceBand priceBand);
    
    /**
     * Returns all the PriceBand instances existing in the repository.
     * @return  a List containing the PriceBands in the repository. The list will be empty if no PriceBand instances are in the repository
     */
    List<PriceBand> findAll();
	
}
