CREATE TABLE products (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          name VARCHAR(255) NOT NULL,
                          price INT NOT NULL,
                          imageUrl VARCHAR(1000)
);

CREATE TABLE options (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         product_id BIGINT,
                         name VARCHAR(255) NOT NULL,
                         price INT NOT NULL,
                         FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE

);
