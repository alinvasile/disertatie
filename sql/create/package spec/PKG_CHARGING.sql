create or replace
PACKAGE PKG_CHARGING AS
          
      TYPE DATE_INTERVAL_TYPE IS TABLE OF DATE;
      
      FUNCTION FN_SPLIT_INTERVAL(from_date DATE, to_date DATE) RETURN DATE_INTERVAL_TYPE;

     /*
      * Computes the default price (price band 1000)
      */
     FUNCTION FN_CALC_DEFAULT_PRICE( FROM_DATE DATE, TO_DATE DATE) RETURN NUMBER;
     
     /*
      * Computes the price using active campaigns.
      * When no active campaign is found, the default price is returned.
      */
     FUNCTION FN_CALC_PRICE( FROM_DATE DATE, TO_DATE DATE) RETURN NUMBER;
     
     /*
      * Charges a created or extended reservation with the given price
      */
     FUNCTION FN_CHARGE(RESERVATION_ID NUMBER, PRICE NUMBER) RETURN VARCHAR2;

END PKG_CHARGING;
