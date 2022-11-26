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