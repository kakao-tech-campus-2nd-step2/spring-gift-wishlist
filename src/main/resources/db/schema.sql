DROP TABLE IF EXISTS products;
CREATE TABLE products (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          name VARCHAR(255),
                          price INT,
                          imageUrl VARCHAR(255)
);

DROP TABLE IF EXISTS users;
CREATE TABLE users (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       password VARCHAR(255),
                       email VARCHAR(255)
);