-- products
CREATE TABLE products (
    id BIGINT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    price INT NOT NULL,
    imageUrl VARCHAR(255)
);

-- members
CREATE TABLE members (
--       id LONG AUTO_INCREMENT PRIMARY KEY,
         email VARCHAR(255) PRIMARY KEY,
         password VARCHAR(255) NOT NULL
);