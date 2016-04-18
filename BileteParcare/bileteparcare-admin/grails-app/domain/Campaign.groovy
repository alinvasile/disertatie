class Campaign {

    String campaignName
    Date campaignFrom
    Date campaignTo
    String details
    
    static mapping = {
		table 'CAMPAIGN'		
		id generator: 'sequence', params:[sequence:'SEQ_CAMPAIGN']
		columns { id column:'CAMPAIGN_ID' }
		version false 
		
	}
	
	static constraints = {       
       campaignName(size: 1..255, blank: false)       
       details(size: 1..1000, blank: false)
    }
    
    String toString() { campaignName }
	
}
