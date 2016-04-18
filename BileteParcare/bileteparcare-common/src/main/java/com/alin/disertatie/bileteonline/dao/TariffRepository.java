package com.alin.disertatie.bileteonline.dao;

import java.util.List;

import com.alin.disertatie.bileteonline.model.Tarriff;

/**
 * <p>
 *   Repository interface handling the tariff data.
 * </p>
 *
 * @author Alin Vasile
 * @version 1.0
 */
public interface TariffRepository {

	/**
     * Adds a new Tarriff instance to the repository
     * @param tariff The tariff to be added
     * @return the same Tarriff instance that was stored in the database  
     */
	Tarriff add(Tarriff tariff);
	
	/**
     * Searches for a Tarriff in the repository, by a given tariff number.
     * @param tariffId The tariffId number being searched
     * @return The matching Tarriff instance or null if not found
     */
	Tarriff findById(long tariffId);
	
	/**
     * Updates an existing tariff in the repository.
     * @param tariff The instance to update the repository
     */
    void update(Tarriff tariff);
    
    /**
     * Returns all the Tarriff instances existing in the repository.
     * @return  a List containing the Tarriffs in the repository. The list will be empty if no Tarriff instances are in the repository
     */
    List<Tarriff> findAll();
	
}
