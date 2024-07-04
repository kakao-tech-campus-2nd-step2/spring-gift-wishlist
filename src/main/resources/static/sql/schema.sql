-- 스키마 구조를 미리 설정하는 sql파일

create table productDto (
     id bigint,
     name varchar(255),
     price int,
     image varchar(255),
     primary key (id)
);