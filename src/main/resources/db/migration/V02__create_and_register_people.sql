create table people(
    id serial primary key,
    name varchar(100) not null,
    street varchar(50),
    house_number varchar(50),
    address_complement varchar(50),
    neighborhood varchar(50),
    post_code varchar(50),
    city varchar(50),
    state varchar(50),
    active boolean not null
);

INSERT INTO people (name, street, house_number, address_complement, neighborhood, post_code, city, state, active) values ('João Silva', 'Rua do Abacaxi', '10', null, 'Brasil', '38.400-12', 'Uberlândia', 'MG', true);
INSERT INTO people (name, street, house_number, address_complement, neighborhood, post_code, city, state, active) values ('Maria Rita', 'Rua do Sabiá', '110', 'Apto 101', 'Colina', '11.400-12', 'Ribeirão Preto', 'SP', true);
INSERT INTO people (name, street, house_number, address_complement, neighborhood, post_code, city, state, active) values ('Pedro Santos', 'Rua da Bateria', '23', null, 'Morumbi', '54.212-12', 'Goiânia', 'GO', true);
INSERT INTO people (name, street, house_number, address_complement, neighborhood, post_code, city, state, active) values ('Ricardo Pereira', 'Rua do Motorista', '123', 'Apto 302', 'Aparecida', '38.400-12', 'Salvador', 'BA', true);
INSERT INTO people (name, street, house_number, address_complement, neighborhood, post_code, city, state, active) values ('Josué Mariano', 'Av Rio Branco', '321', null, 'Jardins', '56.400-12', 'Natal', 'RN', true);
INSERT INTO people (name, street, house_number, address_complement, neighborhood, post_code, city, state, active) values ('Pedro Barbosa', 'Av Brasil', '100', null, 'Tubalina', '77.400-12', 'Porto Alegre', 'RS', true);
INSERT INTO people (name, street, house_number, address_complement, neighborhood, post_code, city, state, active) values ('Henrique Medeiros', 'Rua do Sapo', '1120', 'Apto 201', 'Centro', '12.400-12', 'Rio de Janeiro', 'RJ', true);
INSERT INTO people (name, street, house_number, address_complement, neighborhood, post_code, city, state, active) values ('Carlos Santana', 'Rua da Manga', '433', null, 'Centro', '31.400-12', 'Belo Horizonte', 'MG', true);
INSERT INTO people (name, street, house_number, address_complement, neighborhood, post_code, city, state, active) values ('Leonardo Oliveira', 'Rua do Músico', '566', null, 'Segismundo Pereira', '38.400-00', 'Uberlândia', 'MG', true);
INSERT INTO people (name, street, house_number, address_complement, neighborhood, post_code, city, state, active) values ('Isabela Martins', 'Rua da Terra', '1233', 'Apto 10', 'Vigilato', '99.400-12', 'Manaus', 'AM', true);
INSERT INTO people (name, active) values ('Isabela Martins', true);
INSERT INTO people (name, active) values ('João Silva', true);
INSERT INTO people (name, active) values ('Maria Rita', true);
INSERT INTO people (name, active) values ('Pedro Santos', true);
INSERT INTO people (name, active) values ('Ricardo Pereira', true);
INSERT INTO people (name, active) values ('Josué Mariano', true);
INSERT INTO people (name, active) values ('Pedro Barbosa', true);
INSERT INTO people (name, active) values ('Henrique Medeiros', true);
INSERT INTO people (name, active) values ('Carlos Santana', true);
INSERT INTO people (name, active) values ('Leonardo Oliveira', true);