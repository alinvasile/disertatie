create or replace
PACKAGE BODY pkg_charging AS 

  log_file utl_file.file_type;
  default_charge_log_file utl_file.file_type;
 


  FUNCTION FN_SPLIT_INTERVAL(from_date DATE, to_date DATE) RETURN DATE_INTERVAL_TYPE IS
     dateInterval DATE_INTERVAL_TYPE := DATE_INTERVAL_TYPE ();
     
     numberofdays NUMBER;
     numberofhours NUMBER;
     
     dayCounter NUMBER := 1;
     dateHour NUMBER;
     
     lastDate DATE;
  BEGIN
         
     numberofdays :=  to_date - from_date;
     numberofhours := pkg_reservation.datediff('HH',   from_date,   to_date);
     
     dateInterval.extend;
     dateInterval(dateInterval.count) := from_date;
     
     lastDate:= from_date;
        
     IF numberofdays < 1 THEN   
         -- determine the number of hours till midnight
         -- if less then numberofhours
         -- add to_date
         -- else
         -- add midnight and then to_date
         
         dateHour := to_number(to_char(from_date, 'HH24')) ;
         
         IF (24 - dateHour) < numberofhours THEN
            dateInterval.extend;               
            dateInterval(dateInterval.count) := trunc(lastDate) + 23/24 + 59/1440 + 59/86400;       
         END IF;
         
        
     ELSE
         WHILE dayCounter < numberofdays LOOP
            --lastDate := trunc(lastDate) + 23/24 + 59/1440 + 59/86400;
            lastDate := lastDate + 1;
            dateInterval.extend;
            dateInterval(dateInterval.count) := lastDate ;
            dayCounter := dayCounter + 1;
         END LOOP;  
         
         
     END IF;  
     
     dateInterval.extend;
     dateInterval(dateInterval.count) := to_date;
         
     RETURN dateInterval;
     
  END FN_SPLIT_INTERVAL;


  /*
  * FUTURE : Change number returned into a type, having currency
  */ 
  FUNCTION fn_calc_default_price(from_date DATE,   to_date DATE) RETURN NUMBER AS
  numberofdays NUMBER;
  numberofhours NUMBER;

  dailyprice price_band.price%TYPE := 0;
  dailycurrency price_band.currency%TYPE := 0;

  hourlyprice price_band.price%TYPE := 0;
  hourlycurrency price_band.currency%TYPE := 0;
  BEGIN
   
    default_charge_log_file := utl_file.fopen('LOG_DIR','charge_default.log','A'); 
    
    utl_file.put_line(default_charge_log_file ,'Begin fn_calc_default_price' );
    utl_file.put_line(default_charge_log_file ,'from_date : ' || to_char(from_date,'DD-MM-YYYY HH24:MI' ));
     utl_file.put_line(default_charge_log_file ,'to_date : ' || to_char(to_date,'DD-MM-YYYY HH24:MI' ));
  
   -- numberofdays := round( (to_date - from_date) + 0.01);
    numberofdays := to_date - from_date ;
    numberofhours := pkg_reservation.datediff('HH',   from_date,   to_date);

   
    
    

    -- for days, compute using price band 1

    IF numberofdays >= 0.99 THEN
      SELECT price,
        currency
      INTO dailyprice,
        dailycurrency
      FROM price_band
      WHERE price_band = 1;
    END IF;

    -- for hours, compute using price band 2
    SELECT price,
      currency
    INTO hourlyprice,
      hourlycurrency
    FROM price_band
    WHERE price_band = 2;

    IF numberofdays >= 1 THEN
      numberofdays := ceil(numberofdays);
    ELSE
      numberofdays := 0;
    END IF;

    numberofhours := numberofhours -(numberofdays *24);
    numberofhours := round(numberofhours);
    
    utl_file.put_line(default_charge_log_file ,'numberofdays : ' || numberofdays );
    utl_file.put_line(default_charge_log_file ,'dailyprice : ' || dailyprice );
    utl_file.put_line(default_charge_log_file ,'numberofhours : ' || numberofhours );
    utl_file.put_line(default_charge_log_file ,'hourlyprice : ' || hourlyprice );

    utl_file.fclose(default_charge_log_file);

    RETURN numberofdays * dailyprice + numberofhours *hourlyprice;

  END fn_calc_default_price;

  FUNCTION fn_calc_price(from_date DATE,   to_date DATE) RETURN NUMBER AS
  type arr_campaigns IS varray(50) OF NUMBER;
  campaigns arr_campaigns;

  currentCampaignId campaign.campaign_id%TYPE;
  
  cummulatedDiscount NUMBER := 0;
  totalPrice NUMBER :=0;
  price NUMBER;
  
  currentTarriffId NUMBER;
  currentDiscountId NUMBER;
  
  i NUMBER;
  
  discountType DISCOUNT.DISCOUNT_TYPE%type;
  discountPercentage DISCOUNT.DISCOUNT_PERCENTAGE%type;
  startHour DISCOUNT.START_HOUR%type;
  endHour DISCOUNT.END_HOUR%type; 
  discountCummulated DISCOUNT.DISCOUNT_CUMMULATED%type;
  
  associatedPriceBand NUMBER;
  
  measureUnitId NUMBER;
  measureUnitDesc MEASURE_UNIT.MEASURE_UNIT_DESC%TYPE;
  
  reservationNumberOfHours NUMBER;
  
  pbPrice PRICE_BAND.PRICE%TYPE;
  pbMeasureUnit PRICE_BAND.MEASURE_AMOUNT%TYPE;
  
  dateInterval DATE_INTERVAL_TYPE;
  pivot NUMBER;
  startInterval NUMBER;
  endInterval NUMBER;
  
  lowerDate DATE;
  upperDate DATE;
  
  discountApplicable CHAR := 'N';
  dayOfWeekLower NUMBER;
  dayOfWeekUpper NUMBER;
  
  minimumPrice NUMBER := 0;

  BEGIN
  
    log_file := utl_file.fopen('LOG_DIR','charge.log','A');
  
    utl_file.put_line(log_file,'Begin fn_cal_price(' || to_char(from_date,'DD-MM-YYYY HH24:MI:SS') || ',' || to_char(to_date,'DD-MM-YYYY HH24:MI:SS') || ')');
  
    dateInterval := FN_SPLIT_INTERVAL(from_date,to_date);   
    startInterval := dateInterval.FIRST;
    endInterval := dateInterval.LAST;
        
    -- for each interval
         --   get applicable campaigns
         --   if campaigns found
         --     for each campaign
         --       determine the discount
         --       if discount applicable
         --          calculate cummulated discount
         --       else
         --          determine minimum discount
         --       determine discount type
         --       determine price
         --     determine minimum price
         --   else
         --     determine normal price
         --   return sum of prices
    FOR pivot IN startInterval .. endInterval LOOP
    
       
    
       IF pivot != endInterval THEN
         lowerDate := dateInterval(pivot);
         upperDate := dateInterval(pivot + 1);
         
         utl_file.put_line(log_file,'lowerDate : ' || to_char(lowerDate,'DD-MM-YYYY HH24:MI:SS'));
         utl_file.put_line(log_file,'upperDate : ' || to_char(upperDate,'DD-MM-YYYY HH24:MI:SS'));
         
         -- determine applicable campaigns
         -- glanularity : days
         SELECT campaign_id bulk collect
          INTO campaigns
          FROM campaign
          WHERE lowerDate >= campaign_from AND  upperDate <= campaign_to;
          
         FOR i IN campaigns.FIRST .. campaigns.LAST
         LOOP
              currentCampaignId := campaigns(i);
              
              utl_file.put_line(log_file,'Current campaign id : ' || currentCampaignId);
              
              SELECT TARRIF_ID,DISCOUNT_ID INTO currentTarriffId,currentDiscountId
              FROM CAMPAIGN_DETAILS WHERE CAMPAIGN_ID = currentCampaignId;
              
              utl_file.put_line(log_file,'Current tarriff id : ' || currentTarriffId);
              utl_file.put_line(log_file,'Current discount id : ' || currentDiscountId);
        
              SELECT DISCOUNT_TYPE,DISCOUNT_PERCENTAGE,START_HOUR,END_HOUR,DISCOUNT_CUMMULATED
              INTO discountType, discountPercentage, startHour, endHour,discountCummulated
              FROM DISCOUNT WHERE DISCOUNT_ID = currentDiscountId;
              
              utl_file.put_line(log_file,'discountType : ' || discountType);
              utl_file.put_line(log_file,'discountPercentage : ' || discountPercentage);
              utl_file.put_line(log_file,'startHour : ' || TO_CHAR(startHour,'HH24MI'));
              utl_file.put_line(log_file,'endHour : ' || TO_CHAR(endHour,'HH24MI'));
              utl_file.put_line(log_file,'discountCummulated : ' || discountCummulated);
              
              -- determine discount by discount type : daily, weekend, etc
                    -- IF discountType='Weekend day' THEN
                        -- split interval in days
                        -- for each day
                            --  apply discount if cuurent day is weekend day
                    
                    --  END IF;
                    
              discountApplicable := 'N';
              dayOfWeekLower := to_number(to_char(lowerDate, 'D'));
              dayOfWeekUpper := to_number(to_char(upperDate, 'D'));
                  
              utl_file.put_line(log_file,'dayOfWeekLower : ' || dayOfWeekLower); 
              utl_file.put_line(log_file,'dayOfWeekUpper : ' || dayOfWeekUpper); 
                  
              utl_file.put_line(log_file,'discountType : ' || discountType);
              -- Number of reservations
              -- Week day
              -- Weekend day
              -- Date (DD-MM-YYYY)
              IF discountType='Weekend day' THEN
                  -- determine if the interval is in weekend
                  -- to_char(date,'D') returns the day in week in interval 1-7                  
                   
                  IF (dayOfWeekLower = 7 OR dayOfWeekLower=1) AND (dayOfWeekUpper =7 OR dayOfWeekUpper=1) THEN
                    discountApplicable := 'Y';
                  END IF;
              ELSIF discountType = 'Week day' THEN
                  IF (dayOfWeekLower >=2  AND dayOfWeekLower<=6 ) AND (dayOfWeekUpper >=2  AND dayOfWeekUpper<=6) THEN
                    discountApplicable := 'Y';
                  END IF;
              ELSIF discountType = 'Date' THEN
                 -- consider start date
                 IF TO_CHAR(startHour,'DD-MM-YYYY') = TO_CHAR(lowerDate,'DD-MM-YYYY') AND TO_CHAR(startHour,'DD-MM-YYYY') = TO_CHAR(upperDate,'DD-MM-YYYY') THEN
                    discountApplicable := 'Y';
                 END IF;
              ELSIF discountType = 'Number of reservations' THEN
                 -- unsupported
                 discountApplicable := 'N';             
              END IF;
              
              utl_file.put_line(log_file,'lowerDate hour : ' || TO_Number(TO_CHAR(lowerDate,'HH24MI')));
              utl_file.put_line(log_file,'start hour : ' || TO_Number(TO_CHAR(startHour,'HH24MI')));
              
              utl_file.put_line(log_file,'upperDate hour : ' || TO_Number(TO_CHAR(upperDate,'HH24MI')));
              utl_file.put_line(log_file,'end hour : ' || TO_Number(TO_CHAR(endHour,'HH24MI')));
              
              IF discountApplicable = 'Y' AND TO_Number(TO_CHAR(lowerDate,'HH24MI')) >= TO_Number(TO_CHAR(startHour,'HH24MI')) 
                     AND  
                    TO_Number(TO_CHAR(upperDate,'HH24MI')) <= TO_Number(TO_CHAR(endHour,'HH24MI')) 
                  THEN              
                     
                     
                  
                       -- determine if we have cummulated discount 
                      IF UPPER(discountCummulated) = 'Y' THEN
                        cummulatedDiscount := cummulatedDiscount + discountPercentage;
                      ELSE                      
                        cummulatedDiscount := discountPercentage;
                      END IF; 
                
                
                utl_file.put_line(log_file,'cummulatedDiscount : ' || cummulatedDiscount);
                    
                -- get associated tarrif id
                -- this results in getting price band
                SELECT PRICE_BAND_ID INTO associatedPriceBand FROM TARRIFF WHERE TARRIFF_ID = currentTarriffId;
                
                utl_file.put_line(log_file,'associatedPriceBand : ' || associatedPriceBand);
                      
                -- determine the measure unit and apply it to our reservation interval
                SELECT MEASURE_UNIT_ID,PRICE,measure_amount INTO measureUnitId,pbPrice,pbMeasureUnit FROM PRICE_BAND WHERE PRICE_BAND = associatedPriceBand;
                SELECT MEASURE_UNIT_DESC INTO measureUnitDesc FROM MEASURE_UNIT WHERE MEASURE_UNIT_ID = measureUnitId;
                      
                utl_file.put_line(log_file,'measureUnitId : ' || measureUnitId);      
                utl_file.put_line(log_file,'pbPrice : ' || pbPrice);     
                utl_file.put_line(log_file,'pbMeasureUnit : ' || pbMeasureUnit);  
                utl_file.put_line(log_file,'measureUnitDesc : ' || measureUnitDesc);
                    
                IF measureUnitDesc = 'HOUR' THEN
                   -- compute number of hours in reservation
                   reservationNumberOfHours := PKG_RESERVATION.DATEDIFF('HH',lowerDate,upperDate); 
                   reservationNumberOfHours := ceil(reservationNumberOfHours);
                   
                   utl_file.put_line(log_file,'HOUR : reservationNumberOfHours : ' || reservationNumberOfHours );
                   
                   price := (reservationNumberOfHours / pbMeasureUnit) * pbPrice;  
                   utl_file.put_line(log_file,'HOUR : computed price : ' || price );                   
                   price := price - (price * cummulatedDiscount / 100);
                   utl_file.put_line(log_file,'HOUR : discounted price : ' || price ); 
                ELSIF measureUnitDesc = 'DAY' THEN
                   -- compute number of days in reservation
                   -- or translate in hours
                   reservationNumberOfHours := upperDate - lowerDate ;
                   reservationNumberOfHours := CEIL(reservationNumberOfHours);  
                   
                   utl_file.put_line(log_file,'DAY : reservationNumberOfHours : ' || reservationNumberOfHours );
                   
                   price := reservationNumberOfHours / pbMeasureUnit * pbPrice;         
                   price := price - (price * cummulatedDiscount / 100);
                END IF;
                              
                utl_file.put_line(log_file,'price (discounted): ' || price );
                
                
                
              ELSE
                price := FN_CALC_DEFAULT_PRICE(lowerDate,upperDate);
                utl_file.put_line(log_file,'price (default) : ' || price );
               
              END IF;
              
              IF minimumPrice = 0 OR (minimumPrice> price and price >0) THEN
                 minimumPrice := price;
              END IF;
                     
    END LOOP;
      
      totalPrice := totalPrice + minimumPrice;
      
      END IF;
    END LOOP;   
   
    
    IF totalPrice = 0 THEN
       utl_file.put_line(log_file,'No discounts applicable. Returning default price ');
       totalPrice := FN_CALC_DEFAULT_PRICE(from_date,TO_DATE);
    END IF;
    
    utl_file.put_line(log_file,'Returning  : ' || totalPrice );
    
    utl_file.fclose(log_file);
    
    RETURN totalPrice;

  EXCEPTION
  WHEN OTHERS THEN
      utl_file.put_line(log_file,'Error : ' || SQLCODE || '-' || SQLERRM);
     utl_file.fclose(log_file);
     RETURN FN_CALC_DEFAULT_PRICE(from_date,TO_DATE);

END fn_calc_price;


     FUNCTION FN_CHARGE(RESERVATION_ID NUMBER, PRICE NUMBER) RETURN VARCHAR2
     AS     
     BEGIN
       INSERT INTO 
         CHARGES(CHARGE_ID,CHARGE_DATE,RESERVATION_ID,PRICE)
       VALUES
         (SEQ_CHARGES.NEXTVAL,SYSDATE,RESERVATION_ID,PRICE);
         
      RETURN '[SUCCESS]';
      
      EXCEPTION
      WHEN OTHERS THEN
        RETURN '[FAILURE,' || sqlerrm || ']';
     END FN_CHARGE;


END pkg_charging;
