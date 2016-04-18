
class ActiveCampaign {

	String campaignName
	String campaignTo
	String discountPercentage
	String leftDays
	String details
	
	
    static mapping = {
		table 'VW_ACTIVE_CAMPAIGNS'
		version false 
		cache 'read-only' 	
		id generator: 'assigned' 	
	}
}
