class Discount {

    String discountType
    Double discountPercentage
    Date startHour
    Date endHour
    String discountCummulated
    
    static mapping = {
		table 'discount'		
		id generator: 'sequence', params:[sequence:'SEQ_discount']
		columns { id column:'DISCOUNT_ID' }	
		version false 	
	}
    
    static constraints = {       
       discountType(inList:['Number of reservations','Week day','Weekend day','Date'], blank: false)
       discountPercentage(blank: false)
       discountCummulated(inList:["Y","N"],blank: false)
       
    }  
    
    String toString() { discountType + ' -' + discountPercentage + '% off' }
    
}
