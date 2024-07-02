CREATE TABLE IF NOT EXISTS product (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL CHECK (TRIM(name) <> ''),
    price INT CHECK (price >= 0),
    imageUrl VARCHAR(255) NOT NULL CHECK (TRIM(imageUrl) <> '')
    );