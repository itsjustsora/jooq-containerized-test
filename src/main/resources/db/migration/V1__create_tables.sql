create table users
(
    id         bigserial not null,
    name       varchar   not null,
    email      varchar   not null,
    reg_date timestamp,
    mod_date timestamp,
    primary key (id),
    constraint user_email_unique unique (email)
);

ALTER SEQUENCE users_id_seq RESTART WITH 101;