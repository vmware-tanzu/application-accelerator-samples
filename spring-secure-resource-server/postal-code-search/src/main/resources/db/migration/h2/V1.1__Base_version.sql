create table postalcodeloc (
  postalCode           VARCHAR(15)     NOT NULL PRIMARY KEY,    
  latitude             REAL            NOT NULL,
  longitude            REAL            NOT NULL,
  premium              boolean         NOT NULL
); 
