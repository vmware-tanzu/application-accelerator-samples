CREATE TABLE IF NOT EXISTS availability ( id bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,  searchName VARCHAR(255) NOT NULL, diningName VARCHAR(255) NOT NULL,
    address VARCHAR(255), locality VARCHAR(255), region VARCHAR(255), postalCode VARCHAR(255), phoneNumber VARCHAR(255), reservationURL VARCHAR(1024),
    requestSubject VARCHAR(255) NOT NULL);
    
CREATE TABLE IF NOT EXISTS availability_window (id bigint NOT NULL AUTO_INCREMENT PRIMARY KEY, availabilityId bigint NOT NULL, startTime bigint NOT NULL, 
    endTime bigint NOT NULL);