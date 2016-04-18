CREATE OR REPLACE PACKAGE BODY pkg_archive AS

  

  FUNCTION startJob RETURN binary_integer IS
  BEGIN
    -- start the job
    -- in the next minute
    -- 1 hour interval
   
    vJobNumber := 30;
  
    dbms_job.submit(
            job => vJobNumber,
            next_date => sysdate + 1/(24*60*60),
            interval => 'sysdate+1/24',
            what => 'begin pkg_archive.archiveAuthorisations; end;'
            );
    RETURN vJobNumber;
  
  END startJob;

  PROCEDURE endJob(job_number binary_integer) IS
  BEGIN
     dbms_output.put_line('stopping ' || job_number );
     dbms_job.remove(job_number);
  END;


  PROCEDURE archiveAuthorisations IS
     archiveDate DATE;
  BEGIN
  
     savepoint abcd;
     
     archiveDate := sysdate;
  
      INSERT
        INTO reservation_history
        SELECT *
        FROM reservation
        WHERE nvl(canceled,   'N') = 'Y' OR(reservation_to > archiveDate); 
       
     DELETE FROM reservation WHERE nvl(canceled,   'N') = 'Y' OR(reservation_to > archiveDate);  
     
     INSERT INTO CHARGES_HISTORY SELECT * FROM CHARGES WHERE (CANCELED = 'Y' or RESERVATION_ID IN (SELECT RESERVATION_ID FROM RESERVATION_HISTORY));
     
     DELETE FROM CHARGES WHERE (CANCELED = 'Y' or RESERVATION_ID IN (SELECT RESERVATION_ID FROM RESERVATION_HISTORY));
     
     commit;  
       
     EXCEPTION
       WHEN OTHERS THEN
         rollback to abcd;
         commit;
  END;

END pkg_archive;
