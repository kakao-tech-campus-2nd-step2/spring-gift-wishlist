create table Product
(
    id bigint not null auto_increment,
    name varchar(255) not null,
    price bigint not null,
    imageUrl varchar(255) not null,
    primary key (id)
);