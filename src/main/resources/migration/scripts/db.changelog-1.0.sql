--changeset DB table init
create table person (
    id bigserial primary key,
    linkCode varchar(255),
    originalLink varchar(1024),
    ttl int
);
