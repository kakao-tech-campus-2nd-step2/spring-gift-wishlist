DROP TABLE IF EXISTS products;
CREATE TABLE products (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    price INT,
    imageurl VARCHAR(255)
);
