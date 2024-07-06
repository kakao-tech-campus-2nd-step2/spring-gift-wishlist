CREATE TABLE products (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          name VARCHAR(255) NOT NULL,
                          price INT NOT NULL,
                          imageUrl VARCHAR(1000) DEFAULT NULL
);


CREATE TABLE users (
                       email VARCHAR(255) NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       type ENUM('1', '2') NOT NULL
);

-- 1 : admin 2 :user

CREATE TABLE wishlists (
                        email VARCHAR(255) NOT NULL,
                        type ENUM('1', '2') NOT NULL,
                        productId INT NOT NULL
);
