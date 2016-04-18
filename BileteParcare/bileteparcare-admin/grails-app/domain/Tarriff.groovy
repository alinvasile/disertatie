class Tarriff {

    PriceBand priceBand
    String description
    
    static mapping = {
		table 'TARRIFF'		
		id generator: 'sequence', params:[sequence:'SEQ_TARRIFF']
		columns { id column:'TARRIFF_ID' } 
		priceBand lazy:false, cascade:"none"
		version false 
	}

    static constraints = {       
       description(size: 1..255, blank: false)       
    }
    
    String toString() { description }
    
}
