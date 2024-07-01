CREATE TABLE Product
(
    id       BIGINT AUTO_INCREMENT PRIMARY KEY,
    name     VARCHAR(255) NOT NULL,
    price    DOUBLE       NOT NULL,
    imageUrl VARCHAR(255) NOT NULL
);

CREATE TABLE Employee
(
    id      BIGINT PRIMARY KEY,
    name    VARCHAR(255) NOT NULL,
    email   VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL,
    phone   VARCHAR(255) NOT NULL
);
