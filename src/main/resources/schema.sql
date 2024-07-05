create table products (
  id bigint,
  productName varchar(255),
  price int,
  imageUrl varchar(255),
  amount int,
  primary key (id)
);

create table members (
  email varchar(255),
  password varchar(255),
  primary key (email)
);