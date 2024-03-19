create table auditEvent (
  id                   bigint          NOT NULL AUTO_INCREMENT PRIMARY KEY, 
  context              VARCHAR(255)    NOT NULL,   
  name                 VARCHAR(255)    NOT NULL,  
  principal            VARCHAR(255)    NOT NULL,   
  result               VARCHAR(255)    NOT NULL,
  eventTime            bigint          NOT NULL
);
create index IDX_AUDITEVENT_EVENTTIME ON auditEvent(eventTime);

create table auditData (
  id                   bigint          NOT NULL AUTO_INCREMENT PRIMARY KEY, 
  name                 VARCHAR(255)    NOT NULL,  
  val                  VARCHAR(1024)   NOT NULL,   
  auditId              bigint          NOT NULL
);
create index IDX_AUDITDATA_AUDITID ON auditData(auditId);
 