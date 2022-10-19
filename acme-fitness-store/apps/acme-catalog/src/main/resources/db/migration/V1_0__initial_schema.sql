create table catalog
(
    id                varchar(36) not null primary key,
    description       varchar(1000),
    image_url1        varchar(1000),
    image_url2        varchar(1000),
    image_url3        varchar(1000),
    name              varchar(1000),
    price             double precision,
    short_description varchar(1000),
    tags              varchar(1000)
);