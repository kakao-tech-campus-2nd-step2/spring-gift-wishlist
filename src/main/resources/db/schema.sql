CREATE TABLE Product
(
    id          BIGINT            AUTO_INCREMENT   PRIMARY KEY,
    name        VARCHAR(255)   NOT NULL,
    price       BIGINT         NOT NULL         CHECK (0 <= price),
    imageUrl    VARCHAR(255)   NOT NULL
);
