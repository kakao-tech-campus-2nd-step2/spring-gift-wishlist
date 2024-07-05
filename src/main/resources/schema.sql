create table products
(
    id       bigint auto_increment,
    name     varchar(255),
    price    bigint,
    imageUrl varchar(255),
    primary key (id)
);

create table members
(
    email char(255),
    password varchar(255)
)