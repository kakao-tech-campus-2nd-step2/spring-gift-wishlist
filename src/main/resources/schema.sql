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
    primary key(email)
);
