package com.alin.disertatie.bileteonline.dao;

import java.util.List;

import com.alin.disertatie.bileteonline.model.MeasureUnit;

/**
 * <p>
 *   Repository interface handling the Measure Unit data.
 * </p>
 *
 * @author Alin Vasile
 * @version 1.0
 */
public interface MeasureUnitRepository {
	
	/**
     * Adds a new MeasureUnit instance to the repository
     * @param measureUnit The measure unit to be added
     * @return the updated MeasureUnit instance having the measureUnitId attribute with the new value 
     */
	MeasureUnit add(MeasureUnit measureUnit);

    /**
     * Searches for a MeasureUnit in the repository, by a given measure unit id.
     * @param id The id of the MeasureUnit being searched
     * @return The matching MeasureUnit instance or null if not found
     */
	MeasureUnit findById(long id);

    /**
     * Updates an existing measure unit in the repository.
     * @param measureUnit The instance to update the repository
     */
    void update(MeasureUnit measureUnit);

    /**
     * Returns all the MeasureUnit instances existing in the repository.
     * @return  a List containing the MeasureUnits in the repository. The list will be empty if no MeasureUnit instances are in the repository
     */
    List<MeasureUnit> findAll();

}
