CREATE TABLE IF NOT EXISTS search ( id bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,  name VARCHAR(255) NOT NULL, startTime bigint NOT NULL, endTime bigint NOT NULL, 
    diningTypes VARCHAR(1000), diningNames VARCHAR(1000), postalCode VARCHAR(12) NOT NULL, 
    radius smallint DEFAULT NULL, continousSearch boolean, requestSubject VARCHAR(255) NOT NULL);
