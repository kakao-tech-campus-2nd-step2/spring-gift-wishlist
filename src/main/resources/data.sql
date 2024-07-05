-- products
INSERT INTO products (name, price, image_url) VALUES ('test1', 10000, 'http://');
INSERT INTO products (name, price, image_url) VALUES ('test2', 20000, 'http://');

-- members
INSERT INTO members (email, password, nickname) VALUES ('email', 'password', 'nickname');
INSERT INTO members (email, password, nickname) VALUES ('email1', 'password1', 'nickname1');
INSERT INTO members (email, password, nickname) VALUES ('email2', 'password2', 'nickname2');

-- wishes
INSERT INTO wishes (member_id, product_id, product_count) VALUES (1, 1, 10);
INSERT INTO wishes (member_id, product_id, product_count) VALUES (1, 2, 10);
INSERT INTO wishes (member_id, product_id, product_count) VALUES (2, 1, 10);
INSERT INTO wishes (member_id, product_id, product_count) VALUES (2, 2, 10);


