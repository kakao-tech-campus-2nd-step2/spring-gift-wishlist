CREATE TABLE products
(
    id       BIGINT PRIMARY KEY,
    name     VARCHAR(255),
    price    INT,
    imageUrl VARCHAR(255)
);
CREATE TABLE users(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255) UNIQUE  NOT NULL,
    password VARCHAR(255) NOT NULL
);
CREATE TABLE wishlist(
    user_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    quantity INT NOT NULL,
    PRIMARY KEY (user_id, product_id),
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY  (product_id) REFERENCES  products(id)
);
