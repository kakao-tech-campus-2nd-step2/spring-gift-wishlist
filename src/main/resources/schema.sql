DROP TABLE IF EXISTS PRODUCTS;

CREATE TABLE PRODUCTS (
                    id long NOT NULL AUTO_INCREMENT,
                    name varchar(255) NOT NULL,
                    price long NOT NULL,
                    imageurl varchar(255),
                    primary key (id)
                );