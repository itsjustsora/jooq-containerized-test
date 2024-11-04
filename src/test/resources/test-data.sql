DROP TABLE IF EXISTS member;

-- users 테이블 생성
CREATE TABLE member (
    id SERIAL PRIMARY KEY ,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL
);

INSERT INTO member(first_name, last_name, email) VALUES
    ('Simonne','Stollmeier', 'stollmeier@gmail.com'),
    ('Otes', 'Costain', 'costain@gmail.com');