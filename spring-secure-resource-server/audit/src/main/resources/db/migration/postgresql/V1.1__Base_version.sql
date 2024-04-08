create table auditEvent (
  id                   bigserial       NOT NULL PRIMARY KEY, 
  context              VARCHAR(255)    NOT NULL,  
  name                 VARCHAR(255)    NOT NULL,  
  principal            VARCHAR(255)    NOT NULL,   
  result               VARCHAR(255)    NOT NULL,
  eventTime            bigint          NOT NULL
);
create index IDX_AUDITEVENT_EVENTTIME ON auditEvent(eventTime);

create table auditData (
  id                   bigserial       NOT NULL PRIMARY KEY, 
  name                 VARCHAR(255)    NOT NULL,  
  val                  VARCHAR(1024)   NOT NULL,   
  auditId              bigserial       NOT NULL
);
create index IDX_AUDITDATA_AUDITID ON auditData(auditId);