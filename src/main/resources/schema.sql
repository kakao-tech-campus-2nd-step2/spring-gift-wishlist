CREATE TABLE userInfo (
    email VARCHAR(255) PRIMARY KEY,
    password VARCHAR(255),
    role VARCHAR(50)
);

CREATE TABLE product (
    id bigint PRIMARY KEY,
    name VARCHAR(255),
    price INT,
    imageUrl VARCHAR(255)
);