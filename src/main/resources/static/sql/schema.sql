create table `products` (
                            `id` int auto_increment primary key,
                            `name` varchar(255) not null,
                            `price` int not null,
                            `image_url` varchar(255) not null
);