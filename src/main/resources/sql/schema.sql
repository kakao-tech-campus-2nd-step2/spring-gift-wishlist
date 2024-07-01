drop table if exists product CASCADE;
create table product
(
  id bigint AUTO_INCREMENT PRIMARY KEY,
  name varchar(255),
  price int,
  imageUrl varchar(255)
);