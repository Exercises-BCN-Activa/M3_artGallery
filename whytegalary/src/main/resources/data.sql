DROP TABLE IF EXISTS `product`;
DROP table IF EXISTS `store`;


CREATE TABLE `store` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(250) DEFAULT NULL,
  `capacity` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `creator` varchar(250) DEFAULT NULL,
  `name` varchar(250) DEFAULT NULL,
  `price` decimal(19,2) DEFAULT NULL,
  `date` date DEFAULT CURRENT_TIMESTAMP,
  `update_time` date DEFAULT NULL,
  `update_score` int(11) DEFAULT NULL,
  `store_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `product_fk` FOREIGN KEY (`store_id`) REFERENCES `store` (`id`)
);

insert into store (name, capacity) values ('Whitart Galery Barcelona', 50);
insert into store (name, capacity) values ('Whitart Galery Zaragoza', 40);
insert into store (name, capacity) values ('Whitart Galery Sevilla', 30);
insert into store (name, capacity) values ('Whitart Galery Bilbao', 20);
insert into store (name, capacity) values ('Whitart Galery Madrid', 10);

insert into product (creator, name, price, store_id) values ('Leonardo da Vinci', 'Mona Lisa﻿', 765434.56, 1);
insert into product (creator, name, price, store_id) values ('Michelangelo Buonarotti', 'A Criação de Adão', 93453.43, 1);
insert into product (creator, name, price, store_id) values ('Caravaggio', 'Medusa', 598765.43, 1);
insert into product (creator, name, price, store_id) values ('Diego Velázquez', 'As Meninas', 72345.43, 1);
insert into product (creator, name, price, store_id) values ('Rembrandt', 'A Ronda Noturna', 8234565.43, 1);
insert into product (creator, name, price, store_id) values ('Pablo Picasso', 'Guernica', 72345.43, 1);
insert into product (creator, name, price, store_id) values ('Caravaggio', 'Decapitação de João Batista', 123456.54, 2);
insert into product (creator, name, price, store_id) values ('Peter Paul Rubens', 'O Massacre dos Inocentes', 923456.54, 2);
insert into product (creator, name, price, store_id) values ('Rafael', 'A escola de Atenas', 823456.54, 2);
insert into product (creator, name, price, store_id) values ('Francisco de Goya', 'Os fuzilamentos de três de Maio', 312345.43, 2);
insert into product (creator, name, price, store_id) values ('Édouard Manet', 'Olympia', 72345.43, 2);
insert into product (creator, name, price, store_id) values ('Leonardo da Vinci', 'A última Ceia', 25434.56, 3);
insert into product (creator, name, price, store_id) values ('Jan van Eyck', 'O Casal Arnolfini', 3456776.54, 3);
insert into product (creator, name, price, store_id) values ('Sandro Botticelli', 'O nascimento de Vênus', 35432.34, 3);
insert into product (creator, name, price, store_id) values ('Johannes Vermeer', 'Moça com o Brinco de Pérola', 412345.43, 3);
insert into product (creator, name, price, store_id) values ('Edvard Munch', 'O Grito', 51234.32, 3);
insert into product (creator, name, price, store_id) values ('Vincent van Gogh', 'Terraço do Café à Noite', 12345.43, 3);
insert into product (creator, name, price, store_id) values ('Georges Seurat', 'Uma Tarde de Domingo na Ilha de Grande Jatte', 612342.32, 3);
insert into product (creator, name, price, store_id) values ('Pierre-Auguste Renoir', 'O baile no moulin de la Galette', 71234.23, 4);
insert into product (creator, name, price, store_id) values ('Vincent van Gogh', 'Noite Estrelada', 841234.87, 4);
insert into product (creator, name, price, store_id) values ('René Magritte', 'O Filho do Homem', 876567.98, 4);
insert into product (creator, name, price, store_id) values ('Wassily Kandinsky', 'Composição VIII', 2345432.32, 4);
insert into product (creator, name, price, store_id) values ('Claude Monet', 'Nenúfares', 2345123.37, 4);
insert into product (creator, name, price, store_id) values ('Henri Matisse', 'La Danse', 234564.32, 4);
insert into product (creator, name, price, store_id) values ('Vincent van Gogh', 'Doze girassóis numa jarra', 432234.56, 5);
insert into product (creator, name, price, store_id) values ('René Magritte', 'A Traição das Imagens', 23454.32, 5);
insert into product (creator, name, price, store_id) values ('Salvador Dalí', 'A Persistência da Memória', 43212.34, 5);
insert into product (creator, name, price, store_id) values ('Jackson Pollock', 'No. 5', 123456.54, 5);
insert into product (creator, name, price, store_id) values ('Grant Wood', 'American Gothic', 234545.12, 5);
insert into product (creator, name, price, store_id) values ('Gustav Klimt', 'O beijo', 123456.48, 5);

