-- region creating tables
CREATE TABLE 
    "TARRIFF" (
        "TARRIFF_ID" NUMBER  NOT NULL, 
        "PRICE_BAND_ID" NUMBER  NOT NULL, 
        "DESCRIPTION" VARCHAR(255) NOT NULL) ;
-- endregion creating tables

-- region adding primary keys
ALTER TABLE "TARRIFF" 
    ADD CONSTRAINT "PK_TARRIF_ID" 
            PRIMARY KEY ("TARRIFF_ID") ;
-- endregion adding primary keys

 ALTER TABLE "TARRIFF" ADD CONSTRAINT "FK_PRICE_BAND" FOREIGN KEY(PRICE_BAND_ID ) REFERENCES "PRICE_BAND"  (  "PRICE_BAND"  ) ;

quit;