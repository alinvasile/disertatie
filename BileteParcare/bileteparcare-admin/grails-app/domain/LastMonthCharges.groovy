class LastMonthCharges {

    String vehicleNumber
    String reservationInterval
    String reservationDate
    String price
    
    static mapping = {
		table 'VW_LAST_MONTH_CHARGES'
		version false 
		cache 'read-only' 	
		id generator: 'assigned' 	
	}
}
