class Parking {
    
    String name
    String address
    String city
    String region
    String country
    
    static mapping = {
		table 'PARKING'		
		id generator:'sequence', params:[sequence:'SEQ_PARKING']
		columns { id column:'parking_id' }
		version false 
	}
	
	static constraints = {
        name(size: 1..200, blank: false, unique: true)
        address(size: 1..1000, blank: false)
        city(size: 1..200, blank: false)
        region(size: 1..200, blank: false)
        country(size: 1..200, blank: false)
     }
     
     String toString() { name }
      
    
}
