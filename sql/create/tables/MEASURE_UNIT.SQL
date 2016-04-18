
CREATE TABLE 
    "MEASURE_UNIT" (
        "MEASURE_UNIT_ID" NUMBER NOT NULL, 
        "MEASURE_UNIT_DESC" VARCHAR(255) NOT NULL) ;
-- endregion creating tables

-- region adding primary keys
ALTER TABLE "MEASURE_UNIT" 
    ADD CONSTRAINT "PK_MEASURE_UNIT_ID" 
            PRIMARY KEY ("MEASURE_UNIT_ID") ;
-- endregion adding primary keys

quit;
