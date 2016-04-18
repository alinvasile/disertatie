-- region creating tables
CREATE TABLE 
    "DISCOUNT" (
        "DISCOUNT_ID" NUMBER NOT NULL, 
        "DISCOUNT_TYPE" VARCHAR(255) NOT NULL, 
        "DISCOUNT_PERCENTAGE" NUMBER NOT NULL, 
        "START_HOUR" TIMESTAMP(6) NOT NULL, 
        "END_HOUR" TIMESTAMP(6) NOT NULL, 
        "DISCOUNT_CUMMULATED" CHAR(1) NOT NULL) ;
-- endregion creating tables

-- region adding primary keys
ALTER TABLE "DISCOUNT" 
    ADD CONSTRAINT "PK_DISCOUNT_ID" 
            PRIMARY KEY ("DISCOUNT_ID") ;
-- endregion adding primary keys

quit;
