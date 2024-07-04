CREATE TABLE Product (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          name VARCHAR(255) NOT NULL,
                          price INT NOT NULL CHECK (price >= 0),
                          image_url TEXT,
                          is_active BOOLEAN DEFAULT TRUE
);

CREATE TABLE User (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      email VARCHAR(255) NOT NULL UNIQUE,
                      password VARCHAR(100) NOT NULL,
                      status BOOLEAN NOT NULL DEFAULT TRUE,
                      role VARCHAR(50) NOT NULL DEFAULT 'USER'
);
