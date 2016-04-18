CREATE OR REPLACE PACKAGE PKG_ARCHIVE AS

  vJobNumber binary_integer;

  PROCEDURE archiveAuthorisations ;
  
  FUNCTION startJob RETURN binary_integer ;
  
  PROCEDURE endJob(job_number binary_integer);
  
END PKG_ARCHIVE;
