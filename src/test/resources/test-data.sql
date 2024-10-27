DROP TABLE IF EXISTS users;

-- users 테이블 생성
CREATE TABLE users (
   id         bigserial NOT NULL,
   name       varchar NOT NULL,
   email      varchar NOT NULL,
   reg_date   timestamp,
   mod_date   timestamp,
   PRIMARY KEY (id),
   CONSTRAINT user_email_unique UNIQUE (email)
);

INSERT INTO users(id, name, email) VALUES
    (101,'Siva', 'siva@gmail.com'),
    (102, 'Oleg', 'oleg@gmail.com');