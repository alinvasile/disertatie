CREATE OR REPLACE VIEW VW_LAST_MONTH_CHARGES AS
SELECT rownum as id,VEHICLE_NUMBER, RESERVATION_TO - RESERVATION_FROM RESERVATION_INTERVAL, to_char(RESERVATION_DATE,'DD-MM-YYYY HH24:MI') reservation_date ,
 PRICE
FROM 
 RESERVATION_HISTORY, CHARGES_HISTORY 
WHERE RESERVATION_HISTORY.RESERVATION_ID = CHARGES_HISTORY.RESERVATION_ID
and CHARGES_HISTORY.canceled = 'N'
AND TRUNC(RESERVATION_DATE) between add_months(TRUNC(SYSDATE),-1) and  (TRUNC(SYSDATE) - 1) ;