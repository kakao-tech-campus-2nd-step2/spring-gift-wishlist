drop table if exists product;
create table product(
                        id bigint auto_increment primary key,
                        name varchar(255) not null ,
                        price int not null ,
                        imageUrl varchar(1000) not null
);

drop table if exists member;
create table member(
                        id bigint auto_increment primary key,
                        email varchar(50) unique not null ,
                        password varchar(50) not null
);

