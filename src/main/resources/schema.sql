drop table gift if exists;
drop table users if exists;
create table gift(
    `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
    `name` varchar(255) not null,
    `price` INT not null,
    `imageUrl` varchar(255) not null

);
CREATE TABLE users (
    `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
    `email` VARCHAR(255) NOT NULL,
    `password` VARCHAR(255) NOT NULL,
    `token` VARCHAR(255)
);
