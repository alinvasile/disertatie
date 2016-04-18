class PriceBand {

	Double price	
	String currency	
	MeasureUnit measureUnit
	Double measureAmount	

 	static mapping = {
		table 'PRICE_BAND'		
		id generator: 'sequence', params:[sequence:'SEQ_PRICE_BAND']
		columns { id column:'price_band' } 
		measureUnit lazy:false, cascade:"none"
		version false 
	}

    static constraints = {       
       currency(size: 1..3, blank: false)
       
    }
    
    String toString() { id }
    
}
