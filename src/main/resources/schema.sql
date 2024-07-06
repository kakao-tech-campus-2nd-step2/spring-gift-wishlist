CREATE TABLE Product (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    price BIGINT,
    temperatureOption VARCHAR(255),
    cupOption VARCHAR(255),
    sizeOption VARCHAR(255),
    imageurl VARCHAR(255)
);

CREATE TABLE members (
    id UUID PRIMARY KEY,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL
);
