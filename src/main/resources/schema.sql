DROP TABLE IF EXISTS products;

CREATE TABLE products
(
    id       BIGINT PRIMARY KEY,
    name     VARCHAR(255)  NOT NULL,
    price    BIGINT        NOT NULL,
    imageurl VARCHAR(2083) NOT NULL
);