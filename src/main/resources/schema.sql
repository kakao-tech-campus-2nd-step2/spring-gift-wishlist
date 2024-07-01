CREATE TABLE product (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          name VARCHAR(255) NOT NULL,
                          price BIGINT NOT NULL,
                          description VARCHAR(255),
                          image_url VARCHAR(255)
);
