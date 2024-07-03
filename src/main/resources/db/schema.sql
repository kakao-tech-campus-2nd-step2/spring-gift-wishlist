DROP TABLE product IF EXISTS;
CREATE TABLE product(id SERIAL, name VARCHAR(255), price int, imageUrl varchar(255));
DROP TABLE option IF exists;
CREATE TABLE option (id int, option varchar(255));
DROP TABLE user_tb IF exists;
CREATE TABLE user_tb(id SERIAL, user_id varchar(20), user_pw varchar(20));
