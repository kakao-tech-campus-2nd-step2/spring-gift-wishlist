drop table if exists product;
create table product(
    id bigint auto_increment primary key,
    name varchar(255) not null,
    price int not null ,
    imageUrl varchar(1000) not null,
    createdAt DATETIME default current_timestamp,
    updatedAt DATETIME default current_timestamp on update current_timestamp
);

drop table if exists member;
create table member(
    id bigint auto_increment primary key,
    email varchar(50) unique not null,
    password varchar(50) not null
);

drop table if exists wish;
create table wish(
    id bigint auto_increment primary key,
    product_id bigint not null,
    member_id bigint not null,
    product_count int not null,
    createdAt DATETIME default current_timestamp,
    updatedAt DATETIME default current_timestamp on update current_timestamp,
    foreign key (product_id) references product(id) on delete cascade on update cascade,
    foreign key (member_id) references member(id) on delete cascade on update cascade
);
CREATE INDEX idx_member_id ON wish(member_id);

