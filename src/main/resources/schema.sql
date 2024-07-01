drop table if exists product;
CREATE TABLE product (
    id LONG AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    price INT,
    imageUrl VARCHAR(255)
);