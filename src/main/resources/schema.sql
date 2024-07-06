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
