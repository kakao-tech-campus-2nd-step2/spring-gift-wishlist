drop table if exists product;
create table product (
                          id bigint auto_increment primary key,
                          name varchar(255),
                          price int,
                          imageUrl varchar(255)
);
