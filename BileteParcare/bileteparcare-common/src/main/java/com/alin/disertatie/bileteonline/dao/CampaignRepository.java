package com.alin.disertatie.bileteonline.dao;

import java.util.List;

import com.alin.disertatie.bileteonline.model.Campaign;

/**
 * <p>
 *   Repository interface handling the campaign data.
 * </p>
 *
 * @author Alin Vasile
 * @version 1.0
 */
public interface CampaignRepository {

	/**
     * Adds a new Campaign instance to the repository
     * @param campaign The Campaign to be added
     * @return the same Campaign instance that was stored in the database  
     */
	Campaign add(Campaign campaign);
	
	/**
     * Searches for a Campaign in the repository, by a Campaign id.
     * @param campaignId The campaignId being searched
     * @return The matching Campaign instance or null if not found
     */
	Campaign findById(long campaignId);
	
	/**
     * Updates an existing campaign in the repository.
     * @param campaign The instance to update the repository
     */
    void update(Campaign campaign);
    
    /**
     * Returns all the Campaign instances existing in the repository.
     * @return  a List containing the Campaigns in the repository. The list will be empty if no Campaign instances are in the repository
     */
    List<Campaign> findAll();
	
}
