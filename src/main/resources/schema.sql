CREATE TABLE product (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    price INT NOT NULL,
    image_url VARCHAR(255)
);
CREATE TABLE users (
    email VARCHAR(255) PRIMARY KEY,
    password VARCHAR(255) NOT NULL
);
CREATE TABLE IF NOT EXISTS wishlist (
    user_email VARCHAR(255) NOT NULL,
    product_id BIGINT NOT NULL,
    PRIMARY KEY (user_email, product_id),
    FOREIGN KEY (user_email) REFERENCES users(email),
    FOREIGN KEY (product_id) REFERENCES product(id)
);

