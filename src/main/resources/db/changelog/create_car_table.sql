create table car
(
    id          uuid primary key,
    brand       varchar(100),
    model       varchar(100),
    motorVolume numeric,
    carNumber   numeric,
    price       numeric,
    color       varchar(100)
);