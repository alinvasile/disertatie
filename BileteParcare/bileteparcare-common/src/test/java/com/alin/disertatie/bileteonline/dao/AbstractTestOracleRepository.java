package com.alin.disertatie.bileteonline.dao;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import junit.framework.TestCase;

/**
 * <p>
 *   Abstract base testing class for Oracle repository handling the data.
 * </p>
 *
 * @author Alin Vasile
 * @version 1.0
 */
public abstract class AbstractTestOracleRepository extends TestCase {

	protected static ApplicationContext ctx;

	static{
		ctx = new ClassPathXmlApplicationContext("appContext.xml");
		assertNotNull(ctx);          
	}

	/**
	 * @return A Spring-configured ParkingRepository bean instance 
	 */
	protected ParkingRepository getParkingRepository(){
		return (ParkingRepository) ctx.getBean("repository.parkingOracleRepository");
	}
	
	/**
	 * @return A Spring-configured MeasureUnitRepository bean instance 
	 */
	protected MeasureUnitRepository getMeasureUnitRepository(){
		return (MeasureUnitRepository) ctx.getBean("repository.measureUnitOracleRepository");
	}
	
	/**
	 * @return A Spring-configured PriceBandRepository bean instance 
	 */
	protected PriceBandRepository getPriceBandRepository(){
		return (PriceBandRepository) ctx.getBean("repository.priceBandOracleRepository");
	}
	
	/**
	 * @return A Spring-configured TariffRepository bean instance 
	 */
	protected TariffRepository getTariffRepository(){
		return (TariffRepository) ctx.getBean("repository.tariffOracleRepository");
	}
	
	/**
	 * @return A Spring-configured TariffRepository bean instance 
	 */
	protected CampaignRepository getCampaignRepository(){
		return (CampaignRepository) ctx.getBean("repository.campaignOracleRepository");
	}
	
	

}
