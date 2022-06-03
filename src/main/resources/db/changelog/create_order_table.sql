create table rentalOrder
(
    id uuid primary key,
    rentStart   timestamp with time zone,
    rentEnd     timestamp with time zone,
    client_id   uuid,
    car_id      uuid
);