drop table if exists product cascade;
drop table if exists product_option cascade;
drop table if exists member cascade;

create table product
(
    id        bigint auto_increment not null,
    name      varchar(255) not null,
    price     int          not null,
    image_url varchar(255) not null,
    primary key (id)
);

create table product_option
(
    id               bigint auto_increment not null,
    product_id       bigint       not null,
    name             varchar(255) not null,
    additional_price int          not null,
    primary key (id),
    foreign key (product_id) references product (id)
);

create table member
(
    id       bigint auto_increment not null,
    name     varchar(255) not null,
    email    varchar(255) not null unique,
    password varchar(255) not null,
    role     varchar(255) not null,
    primary key (id)
);
