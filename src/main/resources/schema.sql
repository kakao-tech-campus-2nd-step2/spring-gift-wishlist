CREATE TABLE IF NOT EXISTS product (
    id BIGINT PRIMARY KEY CHECK (id >= 1),
    name VARCHAR(255) NOT NULL,
    price INT NOT NULL,
    image_url VARCHAR(255)
);
