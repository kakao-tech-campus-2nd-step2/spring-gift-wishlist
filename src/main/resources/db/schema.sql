CREATE TABLE if not exists product(
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         name VARCHAR(255) NOT NULL,
                         price INT NOT NULL CHECK (price >= 0),
                         image_url TEXT
);