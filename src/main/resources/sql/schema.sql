DROP TABLE Product IF EXISTS;

CREATE TABLE Product (
    id BIGINT,
    name VARCHAR(255) NOT NULL,
    price BIGINT NOT NULL,
    imageUrl VARCHAR(255),
    primary key (id)
);

CREATE TABLE Member (
    email VARCHAR(255),
    password VARCHAR(50),
    primary key (email)
);