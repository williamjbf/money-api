create table posting(
    id serial primary key,
    description varchar(100) not null ,
    due_date date not null ,
    payment_date date,
    value decimal(10,2) not null,
    note varchar(100),
    type varchar(20) not null,
    id_category int not null,
    id_person int not null,
    foreign key (id_category) REFERENCES category(id),
    foreign key (id_person) references person(id)
);

INSERT INTO posting (description, due_date, payment_date, value, note, type, id_category, id_person) values ('Salário mensal', '2017-06-10', null, 6500.00, 'Distribuição de lucros', 'INCOME', 1, 1);
INSERT INTO posting (description, due_date, payment_date, value, note, type, id_category, id_person) values ('Bahamas', '2017-02-10', '2017-02-10', 100.32, null, 'EXPENSE', 2, 2);
INSERT INTO posting (description, due_date, payment_date, value, note, type, id_category, id_person) values ('Top Club', '2017-06-10', null, 120, null, 'INCOME', 3, 3);
INSERT INTO posting (description, due_date, payment_date, value, note, type, id_category, id_person) values ('CEMIG', '2017-02-10', '2017-02-10', 110.44, 'Geração', 'INCOME', 3, 4);
INSERT INTO posting (description, due_date, payment_date, value, note, type, id_category, id_person) values ('DMAE', '2017-06-10', null, 200.30, null, 'EXPENSE', 3, 5);
INSERT INTO posting (description, due_date, payment_date, value, note, type, id_category, id_person) values ('Extra', '2017-03-10', '2017-03-10', 1010.32, null, 'INCOME', 4, 6);
INSERT INTO posting (description, due_date, payment_date, value, note, type, id_category, id_person) values ('Bahamas', '2017-06-10', null, 500, null, 'INCOME', 1, 7);
INSERT INTO posting (description, due_date, payment_date, value, note, type, id_category, id_person) values ('Top Club', '2017-03-10', '2017-03-10', 400.32, null, 'EXPENSE', 4, 8);
INSERT INTO posting (description, due_date, payment_date, value, note, type, id_category, id_person) values ('Despachante', '2017-06-10', null, 123.64, 'Multas', 'EXPENSE', 3, 9);
INSERT INTO posting (description, due_date, payment_date, value, note, type, id_category, id_person) values ('Pneus', '2017-04-10', '2017-04-10', 665.33, null, 'INCOME', 5, 10);
INSERT INTO posting (description, due_date, payment_date, value, note, type, id_category, id_person) values ('Café', '2017-06-10', null, 8.32, null, 'EXPENSE', 1, 5);
INSERT INTO posting (description, due_date, payment_date, value, note, type, id_category, id_person) values ('Eletrônicos', '2017-04-10', '2017-04-10', 2100.32, null, 'EXPENSE', 5, 4);
INSERT INTO posting (description, due_date, payment_date, value, note, type, id_category, id_person) values ('Instrumentos', '2017-06-10', null, 1040.32, null, 'EXPENSE', 4, 3);
INSERT INTO posting (description, due_date, payment_date, value, note, type, id_category, id_person) values ('Café', '2017-04-10', '2017-04-10', 4.32, null, 'EXPENSE', 4, 2);
INSERT INTO posting (description, due_date, payment_date, value, note, type, id_category, id_person) values ('Lanche', '2017-06-10', null, 10.20, null, 'EXPENSE', 4, 1);