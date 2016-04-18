class MeasureUnit {

	String measureUnitDesc

    static mapping = {
		table 'MEASURE_UNIT'		
		id generator:'sequence', params:[sequence:'SEQ_MEASURE_UNIT']
		columns { id column:'measure_unit_id' }
		version false 
	}
	
	 static constraints = {
        measureUnitDesc(size: 1..255, blank: false, unique: true)
     }
     
    String toString() { measureUnitDesc }
      
    
    
}
