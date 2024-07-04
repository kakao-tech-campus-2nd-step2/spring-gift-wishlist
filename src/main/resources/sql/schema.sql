drop table if exists product;
drop table if exists users;

create table product (
                          id bigint auto_increment primary key,
                          name varchar(255) not null ,
                          price int not null ,
                          imageUrl varchar(255) not null
);

create table users (
                        id bigint auto_increment primary key,
                        password varchar(255) not null ,
                        email varchar(255) not null
);

CREATE TABLE user_product (
                             order_id bigint,
                             product_id bigint,
                             PRIMARY KEY (order_id, product_id),
                             FOREIGN KEY (order_id) REFERENCES orders(id),
                             FOREIGN KEY (product_id) REFERENCES products(id)
);