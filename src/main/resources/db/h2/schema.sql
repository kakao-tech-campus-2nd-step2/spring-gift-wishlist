-- Drop the table if it exists
DROP TABLE IF EXISTS PRODUCT;

-- Create the table
CREATE TABLE PRODUCT (
     id BIGINT AUTO_INCREMENT PRIMARY KEY,
     name VARCHAR(255) NOT NULL,
     price INT NOT NULL,
     image_url VARCHAR(255)
);
