CREATE DATABASE IF NOT EXISTS store;
use store;

CREATE TABLE IF NOT EXISTS client(
id int unique auto_increment primary key,
name varchar(60),
address varchar(50),
email varchar(25)
);

CREATE TABLE IF NOT EXISTS product(
id int unique auto_increment primary key,
nume varchar(30),
pret int,
stoc int
);

CREATE TABLE IF NOT EXISTS orderr(
id int unique auto_increment primary key,
idClient int,
idProduct int,
quantity int,
totalPrice int default 0,
FOREIGN KEY(idClient) references client(id),
FOREIGN KEY(idProduct) references product(id)
);

INSERT INTO client (id,name,address,email) VALUES(1,'Anita', 'Observator', 'an_ita@yahoo.com');
INSERT INTO client(id,name,address,email) VALUES (2, 'Bogdan', 'Marasti', 'bogdan@g.com');
INSERT INTO client(id, name, address, email) VALUES(3,'Sorin', 'Gherla, CJ', 'so_so00@y.com');
INSERT INTO client (id,name, address,email) VALUES(4, 'Calin', 'Floresti, CJ', 'f005@gmail.com');

INSERT INTO product (id,nume,pret,stoc) VALUES (1, 'Geanta',300,50);
INSERT INTO product (id,nume,pret,stoc) VALUES (2, 'Pantofi',250,15);
INSERT INTO product (id,nume,pret,stoc) VALUES (3, 'Esarfa',100,30);

