drop table if exists product CASCADE;
create table product
(
    id       bigint AUTO_INCREMENT PRIMARY KEY,
    name     varchar(255),
    price    int,
    imageUrl varchar(255)
);

drop table if exists account CASCADE;
create table account
(
    id       bigint AUTO_INCREMENT PRIMARY KEY,
    email    varchar(255),
    password varchar(255)
);