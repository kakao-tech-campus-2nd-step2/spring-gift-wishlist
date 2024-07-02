drop table if exists product;
create table product(
                        id bigint auto_increment primary key,
                        name varchar(255) not null ,
                        price int not null ,
                        imageUrl varchar(1000) not null
);
