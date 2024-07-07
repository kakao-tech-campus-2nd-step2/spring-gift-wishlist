create table products (
    id bigint auto_increment,
    name varchar(255),
    price bigint,
    image_url varchar(255),
    primary key (id)
);

creat table members (
      id bigint auto_increment,
      email varchar(255),
      password varchar(255),
      role varchar(255) default "user",
      primary key (id)
)