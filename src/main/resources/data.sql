-- 다양한 종류의 상품을 간단하고 중복 없는 imageUrl로 작성해서 입력하기 총 10개


-- 상품 데이터 입력
INSERT INTO product (name, price, imageUrl)
VALUES ('키보드', 10000, 'https://www.google.com/keyboard.png'),
         ('마우스', 5000, 'https://www.google.com/mouse.png'),
         ('모니터', 20000, 'https://www.google.com/monitor.png'),
         ('노트북', 30000, 'https://www.google.com/laptop.png'),
         ('키보드2', 10000, 'https://www.google.com/keyboard2.png'),
         ('마우스2', 5000, 'https://www.google.com/mouse2.png'),
         ('모니터2', 20000, 'https://www.google.com/monitor2.png'),
         ('노트북2', 30000, 'https://www.google.com/laptop2.png'),
         ('키보드3', 10000, 'https://www.google.com/keyboard3.png'),
         ('마우스3', 5000, 'https://www.google.com/mouse3.png');


-- 회원 데이터 입력: 관리자 1명, 사용자 3명
INSERT INTO member (name, email, password, role)
VALUES ('admin', 'admin@email.com', 'admin', 'ADMIN'),
       ('user1', 'user1@email.com', 'password1', 'USER'),
       ('user2', 'user2@email.com', 'password2', 'USER'),
       ('user3', 'user3@email.com', 'password3', 'USER');