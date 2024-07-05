DROP TABLE IF EXISTS products CASCADE;
CREATE TABLE products (
    id LONG PRIMARY KEY ,
    name VARCHAR(255),
    price INT,
    imageUrl VARCHAR(255)
);

DROP TABLE IF EXISTS users CASCADE;
CREATE TABLE users (
    id LONG AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255),
    password VARCHAR(255)
);

DROP TABLE IF EXISTS wishlist CASCADE;
CREATE TABLE wishlist (
    id LONG AUTO_INCREMENT PRIMARY KEY,
    user_id LONG,
    product_id LONG,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (product_id) REFERENCES products(id)
);
