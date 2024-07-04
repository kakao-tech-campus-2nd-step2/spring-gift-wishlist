drop table if exists product;
drop table if exists users;

create table product (
                          id bigint auto_increment primary key,
                          name varchar(255),
                          price int,
                          imageUrl varchar(255)
);

create table users (
                        password varchar(255),
                        email varchar(255) primary key
);