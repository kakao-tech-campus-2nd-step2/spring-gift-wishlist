create table product(
    id long not null,
    name varchar(255),
    price int,
    url varchar(255),
    primary key(id)
);

create table member(
    email varchar(255) not null,
    password varchar(255) not null,
    role boolean,
    primary key(id)
);

create table wishlist(
    member_id varchar(255) not null,
    product_id long not null,
    primary key(member_id)
);
