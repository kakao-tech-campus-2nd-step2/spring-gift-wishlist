drop table if exists product CASCADE;
create table products_tb
(
    id bigint AUTO_INCREMENT PRIMARY KEY,
    name varchar(50),
    price int,
    imageUrl varchar(255)
);