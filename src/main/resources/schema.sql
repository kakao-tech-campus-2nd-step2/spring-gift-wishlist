drop table if exists product cascade;
drop table if exists product_option cascade;
drop table if exists member cascade;

create table product
(
    id        bigint auto_increment not null,
    name      varchar(255),
    price     int,
    image_url varchar(255),
    primary key (id)
);

create table product_option
(
    id               bigint auto_increment not null,
    product_id       bigint,
    name             varchar(255),
    additional_price int,
    primary key (id),
    foreign key (product_id) references product (id)
);

create table member
(
    id       bigint auto_increment not null,
    name     varchar(255),
    email    varchar(255),
    password varchar(255),
    role     varchar(255),
    primary key (id)
);
