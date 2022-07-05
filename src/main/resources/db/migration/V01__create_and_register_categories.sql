CREATE TABLE category(
    id serial primary key ,
    name varchar(50) not null
);

insert into category(name) values ('Leisure');
insert into category(name) values ('Food');
insert into category(name) values ('Supermarket');
insert into category(name) values ('Pharmacy');
insert into category(name) values ('Others');
