CREATE TABLE Product (
                         id BIGINT PRIMARY KEY,
                         name VARCHAR(255) NOT NULL,
                         price DOUBLE NOT NULL,
                         imageUrl VARCHAR(255) NOT NULL
);

CREATE TABLE Customer (
                          id BIGINT PRIMARY KEY,
                          firstName VARCHAR(255) NOT NULL,
                          lastName VARCHAR(255) NOT NULL
);
