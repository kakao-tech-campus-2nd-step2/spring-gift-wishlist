CREATE TABLE product (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    price INT,
    imageUrl VARCHAR(255)
);

CREATE TABLE member (
    password VARCHAR(255),
    email VARCHAR(255)
);

