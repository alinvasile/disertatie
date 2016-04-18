
CREATE TABLE 
    "PARKING_CAMPAIGN" (
        "CAMPAIGN_ID" NUMBER NOT NULL, 
        "PARKING_ID" NUMBER NOT NULL) ;
-- endregion creating tables

-- region adding primary keys
ALTER TABLE "PARKING_CAMPAIGN" 
    ADD CONSTRAINT "PK_PARKING_CAMPAIGN" 
            PRIMARY KEY (CAMPAIGN_ID,PARKING_ID) ;
-- endregion adding primary keys

quit;
