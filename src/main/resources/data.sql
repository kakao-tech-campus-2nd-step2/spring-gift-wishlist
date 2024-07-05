-- products
INSERT INTO products (name, price, image_url) VALUES ('test1', 10000, 'http://');
INSERT INTO products (name, price, image_url) VALUES ('test2', 20000, 'http://');

-- members
INSERT INTO members (email, password, nickname) VALUES ('email', 'password', 'nickname');

-- wishes
INSERT INTO wishes (member_id, product_id, product_count) VALUES (1, 1, 10);
INSERT INTO wishes (member_id, product_id, product_count) VALUES (1, 2, 10);

