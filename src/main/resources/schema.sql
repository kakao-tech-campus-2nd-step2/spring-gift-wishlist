CREATE TABLE product (
                         id BIGINT PRIMARY KEY AUTO_INCREMENT,
                         name VARCHAR(255) NOT NULL,
                         price INT NOT NULL,
                         imageUrl VARCHAR(255)
);

CREATE TABLE member (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        email VARCHAR(255) NOT NULL,
                        password VARCHAR(255) NOT NULL
);