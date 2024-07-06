-- Insert test data into PRODUCT table
INSERT INTO PRODUCT (name, price, image_url) VALUES ('아이스 아메리카노', 1000, 'image1.jpg');
INSERT INTO PRODUCT (name, price, image_url) VALUES ('아이스 라떼', 2000, 'image2.jpg');
INSERT INTO PRODUCT (name, price, image_url) VALUES ('아이스크림', 3000, 'image3.jpg');

-- Insert test data into MEMBER table
INSERT INTO MEMBER (email, password, role) VALUES ('test@naver.com', '123', 'USER');
INSERT INTO MEMBER (email, password, role) VALUES ('jane@example.com', '123', 'ADMIN');

-- Insert test data into WISH_PRODUCT table
INSERT INTO WISH_PRODUCT (member_id, product_id) VALUES ('test@naver.com', 1);
INSERT INTO WISH_PRODUCT (member_id, product_id) VALUES ('test@naver.com', 2);
INSERT INTO WISH_PRODUCT (member_id, product_id) VALUES ('jane@example.com', 3);
