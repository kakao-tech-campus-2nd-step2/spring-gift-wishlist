drop table if exists products_tb CASCADE;
create table products_tb
(
    id bigint AUTO_INCREMENT PRIMARY KEY,
    name varchar(50),
    price int,
    imageUrl varchar(255)
);

drop table if exists user_tb CASCADE;
create table user_tb
(
    id bigint AUTO_INCREMENT PRIMARY KEY,
    email varchar(50),
    password varchar(20),
    accessToken varchar(255)
);