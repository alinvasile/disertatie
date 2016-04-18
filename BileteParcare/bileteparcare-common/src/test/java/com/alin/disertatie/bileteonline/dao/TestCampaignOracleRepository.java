package com.alin.disertatie.bileteonline.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.alin.disertatie.bileteonline.model.Campaign;

/**
 * <p>
 *   Test class for Oracle-based repository handling the MeasureUnit data.
 * </p>
 *
 * @author Alin Vasile
 * @version 1.0
 */
public class TestCampaignOracleRepository extends AbstractTestOracleRepository  {

	/**
	 * Test the measure unit oracle repository functionality.
	 */
	@Test
	public void testCampaignOracleRepository(){
		Campaign campaign = new Campaign();
		
		campaign.setCampaignName("PARKING GOLD TICKET");
		campaign.setDetails("For 5 tickets you get one free");
		campaign.setCampaignFrom(new Date());
		Calendar instance = Calendar.getInstance();
		instance.add(Calendar.MONTH, 1);
		campaign.setCampaignTo(instance.getTime());
		
		campaign = getCampaignRepository().add(campaign);
		assertTrue(campaign.getCampaignId() > 0);
		
		campaign.setCampaignName("RENAMED GOLD AND PLATINUM");
		getCampaignRepository().update(campaign);
		
		Campaign campaignUpdated = getCampaignRepository().findById(campaign.getCampaignId());
		assertEquals(campaign.getCampaignId(),campaignUpdated.getCampaignId());
		assertEquals("RENAMED GOLD AND PLATINUM",campaignUpdated.getCampaignName());
		assertEquals(campaign.getDetails(),campaignUpdated.getDetails());
		assertEquals(campaign.getCampaignFrom(),campaignUpdated.getCampaignFrom());
		assertEquals(campaign.getCampaignTo(),campaignUpdated.getCampaignTo());
		
		boolean found = false;
		List<Campaign> campaignList = getCampaignRepository().findAll();
		for(Campaign m: campaignList){
			if(m.getCampaignId() == campaign.getCampaignId()){
				found = true;
			}
		}
		
		assertTrue(found);
		
		assertNull(getCampaignRepository().findById(-1));
		
	}
		
	
	
}
