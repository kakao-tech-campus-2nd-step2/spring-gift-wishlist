create table products (
  id bigint,
  productName varchar(255),
  price int,
  imageUrl varchar(255),
  amount int,
  primary key (id)
);

create table members (
  email varchar(255) UNIQUE NOT NULL,
  password varchar(255) NOT NULL,
  role VARCHAR(50) NOT NULL,
  primary key (email)
);