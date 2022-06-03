create table client
(
    id              uuid primary key,
    licenceNumber   varchar(100),
    name            varchar(100),
    age             numeric,
    experience      numeric,
    phone           varchar(20)
);