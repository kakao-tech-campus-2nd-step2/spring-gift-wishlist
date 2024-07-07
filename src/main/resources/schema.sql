
CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(255) NOT NULL
);


CREATE TABLE products (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    price INT NOT NULL,
    imageUrl VARCHAR(255)
);


CREATE TABLE wishList (
    user_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, product_id),
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (product_id) REFERENCES products(id)
);


INSERT INTO users (email, password, role) VALUES
                                              ('john.doe@example.com', 'password123', 'USER'),
                                              ('jane.doe@example.com', 'password456', 'ADMIN'),
                                              ('alice.smith@example.com', 'password789', 'USER'),
                                              ('bob.jones@example.com', 'password101', 'USER');


INSERT INTO products (name, price, imageUrl) VALUES
                                                 ('Laptop', 1200, 'http://example.com/images/laptop.jpg'),
                                                 ('Smartphone', 800, 'http://example.com/images/smartphone.jpg'),
                                                 ('Tablet', 600, 'http://example.com/images/tablet.jpg'),
                                                 ('Headphones', 150, 'http://example.com/images/headphones.jpg'),
                                                 ('Smartwatch', 200, 'http://example.com/images/smartwatch.jpg');


INSERT INTO wishList (user_id, product_id) VALUES
                                               (1, 1),
                                               (1, 3),
                                               (2, 2),
                                               (2, 4),
                                               (3, 1),
                                               (3, 2),
                                               (3, 5),
                                               (4, 3),
                                               (4, 4);