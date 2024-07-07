CREATE TABLE IF NOT EXISTS products(
    id BIGINT PRIMARY KEY,
    name VARCHAR(255),
    price INT,
    image_url VARCHAR(255)
);


CREATE TABLE IF NOT EXISTS members (
    id IDENTITY PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE wish_lists (
                            id BIGINT AUTO_INCREMENT PRIMARY KEY,
                            member_id BIGINT,
                            FOREIGN KEY (member_id) REFERENCES members (id)
);

CREATE TABLE wish_list_items (
     id BIGINT AUTO_INCREMENT PRIMARY KEY,
     wish_list_id BIGINT,
     product_id BIGINT,
     quantity INT,
     FOREIGN KEY (wish_list_id) REFERENCES wish_lists(id),
     FOREIGN KEY (product_id) REFERENCES products(id)
);