drop table if exists product cascade;
drop table if exists member cascade;

create table product (
    id bigint generated by default as identity,
    name varchar(255),
    price int,
    imageUrl varchar(255),
    primary key (id)
);

-- 이메일은 중복되면 안되므로 unique 제약조건을 추가합니다.
create table member (
    id bigint generated by default as identity,
    name varchar(255),
    email varchar(255) unique,
    password varchar(255),
    role varchar(255),
    primary key (id)
);