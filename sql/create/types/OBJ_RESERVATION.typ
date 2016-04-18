create or replace type OBJ_RESERVATION as object
(
  -- Author  : VASILE ALIN
  -- Created : 02.04.2009 22:27:24
  -- Purpose : Reservation object
  
  -- Attributes
  PARKING NUMBER, 
	RESERVATION_DATE TIMESTAMP(6) , 
	RESERVATION_FROM TIMESTAMP(6) , 
	RESERVATION_ID NUMBER , 
	RESERVATION_TO TIMESTAMP(6) , 
	VEHICLE_NUMBER VARCHAR2(100) , 
	CANCELED CHAR(1),
  
  -- Member functions and procedures
  
  -- Returns 'Y' if the current reservation is expired, 'N' otherwise
  member function IS_EXPIRED RETURN CHAR
) 
/
create or replace type body OBJ_RESERVATION is
  
  -- Member procedures and functions
  member function IS_EXPIRED RETURN CHAR IS
  begin
      IF RESERVATION_TO > SYSDATE THEN
         RETURN 'N';
      ELSE
         RETURN 'Y';
      END IF;
  end;
  
end;
/
