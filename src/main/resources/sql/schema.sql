drop table if exists product;
drop table if exists users;

create table product (
                          id bigint auto_increment primary key,
                          name varchar(255) not null ,
                          price int not null ,
                          imageUrl varchar(255) not null
);

create table users (
                        password varchar(255) not null ,
                        email varchar(255) not null primary key
);
