DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS users;

CREATE TABLE products
(
    id       BIGINT PRIMARY KEY,
    name     VARCHAR(255)  NOT NULL,
    price    BIGINT        NOT NULL,
    imageurl VARCHAR(2083) NOT NULL
);

CREATE TABLE users
(
    email    VARCHAR(255) PRIMARY KEY,
    password VARCHAR(72) NOT NULL
);

CREATE TABLE wishlists (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          user_email VARCHAR(255),
                          product_id BIGINT,
                          FOREIGN KEY (user_email) REFERENCES user(email),
                          FOREIGN KEY (product_id) REFERENCES product(id)
);