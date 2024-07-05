CREATE TABLE product
(
    id       INT AUTO_INCREMENT PRIMARY KEY,
    name     VARCHAR(255),
    price    INT,
    imageUrl VARCHAR(255)
);

CREATE TABLE member
(
    email    VARCHAR(255) PRIMARY KEY,
    password VARCHAR(255)
);