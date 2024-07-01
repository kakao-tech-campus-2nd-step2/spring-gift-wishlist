DROP TABLE IF EXISTS product;

CREATE TABLE product (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         name VARCHAR(255) NOT NULL,
                         price INT NOT NULL,
                         imageUrl VARCHAR(255)
);
