create or replace package PKG_RESERVATION is

  -- Author  : VASILE ALIN
  -- Created : 02.04.2009 22:19:25
  -- Purpose : Exposes basic reservation operations.
  
  -- Public type declarations
  -- type <TypeName> is <Datatype>;
  
  -- Public constant declarations
  -- <ConstantName> constant <Datatype> := <Value>;

  -- Public variable declarations
  -- <VariableName> <Datatype>;

  -- Public function and procedure declarations
  
  function datediff( p_what in varchar2,
                                         p_d1   in date,
                                         p_d2   in date ) return number;
  
  /*
   * Creates a new reservation based on the details provided by the OBJ_RESERVATION argument
   */
  PROCEDURE make_reservation( reservationObject IN OUT OBJ_RESERVATION );
  
  /*
   * Extends an existing reservation.
   * The following text can be returned :
   *   - [SUCCESS]
   *   - [FAILED,<Reason text>]
   */
  function extend_reservation(P_RESERVATION_ID NUMBER, P_RESERVATION_TO TIMESTAMP) RETURN CHAR;
  
  /*
   * Checks that a valid reservation exists based on the vehicle number.
   * The following text can be returned :
   *   - [VALID]
   *   - [INVALID,<Reason text>]
   */
  function check_reservation(P_VEHICLE_NUMBER VARCHAR2) RETURN VARCHAR2; 
  
  /*
   * Checks that a valid reservation exists based on the reservation id number.
   * The following text can be returned :
   *   - [VALID]
   *   - [INVALID,<Reason text>]
   */
  function check_reservation(P_RESERVATION_ID NUMBER) RETURN VARCHAR2;
  
  /*
   * Cancels an existing reservation, based on the vehicle number
   * The following text can be returned :
   *   - [SUCCESS]
   *   - [FAILED,<Reason text>]
   */
  function cancel_reservation(P_VEHICLE_NUMBER VARCHAR2) RETURN VARCHAR2; 
  
  /*
   * Cancels an existing reservation, based on the reservation id number.
   * The following text can be returned :
   *   - [SUCCESS]
   *   - [FAILED,<Reason text>]
   */
  function cancel_reservation(P_RESERVATION_ID NUMBER) RETURN VARCHAR2; 

end PKG_RESERVATION;
/
