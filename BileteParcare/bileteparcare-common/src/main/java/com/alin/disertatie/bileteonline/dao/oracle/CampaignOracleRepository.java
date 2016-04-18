package com.alin.disertatie.bileteonline.dao.oracle;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;

import com.alin.disertatie.bileteonline.dao.CampaignRepository;
import com.alin.disertatie.bileteonline.model.Campaign;

/**
 * <p>
 *   Oracle-based implementation repository interface handling the campaign data.
 * </p>
 *
 * @author Alin Vasile
 * @version 1.0
 */
public class CampaignOracleRepository extends AbstractOracleRepository implements CampaignRepository  {

	public Campaign add(Campaign campaign) {
		long campaignId = getJdbcTemplate().queryForLong("SELECT SEQ_CAMPAIGN.nextval FROM DUAL");
		campaign.setCampaignId(campaignId);
        getJdbcTemplate().update("INSERT INTO CAMPAIGN(CAMPAIGN_ID,CAMPAIGN_NAME,CAMPAIGN_FROM,CAMPAIGN_TO,DETAILS) VALUES (?,?,?,?,?) ",
                new Object[]{campaign.getCampaignId(),campaign.getCampaignName(),new java.sql.Timestamp(campaign.getCampaignFrom().getTime()),new java.sql.Timestamp(campaign.getCampaignTo().getTime()),campaign.getDetails()});
        return campaign;
	}

	public List<Campaign> findAll() {
		return getSimpleJdbcTemplate().query("SELECT CAMPAIGN_ID,CAMPAIGN_NAME,CAMPAIGN_FROM,CAMPAIGN_TO,DETAILS FROM CAMPAIGN",ParameterizedBeanPropertyRowMapper.newInstance(Campaign.class));
	}

	public Campaign findById(long campaignId) {
		try{
    		return getSimpleJdbcTemplate().queryForObject("SELECT CAMPAIGN_ID,CAMPAIGN_NAME,CAMPAIGN_FROM,CAMPAIGN_TO,DETAILS FROM CAMPAIGN WHERE CAMPAIGN_ID=?", ParameterizedBeanPropertyRowMapper.newInstance(Campaign.class),campaignId );
    	} catch(EmptyResultDataAccessException e){
    		return null;
    	}
	}

	public void update(Campaign campaign) {
		getSimpleJdbcTemplate().update("UPDATE CAMPAIGN SET CAMPAIGN_NAME=?,campaign_FROM=?,CAMPAIGN_TO=?,DETAILS=? WHERE CAMPAIGN_ID = ? ",
				campaign.getCampaignName(),new java.sql.Timestamp(campaign.getCampaignFrom().getTime()),new java.sql.Timestamp(campaign.getCampaignTo().getTime()),campaign.getDetails(),campaign.getCampaignId());
  
		
	}

}
