drop table gift if exists;
create table gift(
    `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
    `name` varchar(255) not null,
    `price` INT not null,
    `imageUrl` varchar(255)

);