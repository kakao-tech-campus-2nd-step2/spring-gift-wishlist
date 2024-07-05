DROP TABLE products IF EXISTS;

CREATE TABLE products(
                id BIGINT,
                name VARCHAR(255),
                price INT,
                image_url VARCHAR(255),
                PRIMARY KEY (id)
            );


DROP TABLE users IF EXISTS;

CREATE TABLE users(
                id BIGINT AUTO_INCREMENT,
                email VARCHAR(255),
                password VARCHAR(255),
                name VARCHAR(255),
                role VARCHAR(255),
                PRIMARY KEY (id)
            );


DROP TABLE wishes IF EXISTS;

CREATE TABLE wishes(
                product_name VARCHAR(255),
                count VARCHAR(255),
                user_id INT
            );
