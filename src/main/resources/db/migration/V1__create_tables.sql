CREATE TABLE member (
       id SERIAL PRIMARY KEY ,
       first_name VARCHAR(50) NOT NULL,
       last_name VARCHAR(50) NOT NULL,
       email VARCHAR(50) NOT NULL
);