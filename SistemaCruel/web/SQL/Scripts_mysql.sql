/*BANCO DE DADOS USADO -> MYSQL WorkBench*/

drop database if exists trabalhoFinalWeb2Denovo;

create database trabalhoFinalWeb2Denovo;

use trabalhoFinalWeb2Denovo;

drop table if exists Registro;
drop table if exists Cardapio;
drop table if exists Atendente;
drop table if exists Servidor;
drop table if exists Nutricionista;
drop table if exists Professor;
drop table if exists Gerente;
drop table if exists Externo;
drop table if exists Servidor;
drop table if exists Janta;
drop table if exists Almoco;
drop table if exists Ingrediente;
drop table if exists Cidade;
drop table if exists Ingrediente;
drop table if exists Estado;

create table TipoIngrediente(
id integer auto_increment key,
nome varchar(50)
);

create table Ingrediente(
id integer auto_increment primary key,
nome varchar(50),
idTipoIngrediente integer,
constraint fk_ingrediente_tipoIngrediente foreign key(idTipoIngrediente) references TipoIngrediente(id)
);

create table Estado(
id integer auto_increment primary key,
nome varchar(50)
);

create table Cidade(
id integer auto_increment primary key,
nome varchar(50),
idEstado integer,
constraint fk_cidade_estado foreign key(idEstado) references Estado(id)
);

create table Atendente(
id integer auto_increment primary key,
nome varchar(50),
cpf varchar(14),
dataNascimento date,
idCidade integer,
Rua varchar(60),
cep varchar(9),
email varchar(50),
estadoCadastro bit,
senha varchar(50),
constraint fk_atendente_cidade foreign key(idCidade) references Cidade(id)
);

create table Aluno(
id integer auto_increment primary key,
nome varchar(50),
cpf varchar(14),
dataNascimento date,
idCidade integer,
Rua varchar(60),
cep varchar(9),
email varchar(50),
estadoCadastro bit,
AnoIngresso datetime,
grr varchar(8),
constraint fk_aluno_cidade foreign key(idCidade) references Cidade(id)
);

create table Externo(
idAtendente integer auto_increment primary key,
nome varchar(50),
cpf varchar(14),
dataNascimento date,
idCidade integer,
Rua varchar(60),
cep varchar(9),
email varchar(50),
estadoCadastro bit,
Observacao varchar(50),
constraint fk_externo_cidade foreign key(idCidade) references Cidade(id)
);

create table Gerente(
id integer auto_increment primary key,
nome varchar(50),
cpf varchar(14),
dataNascimento date,
idCidade integer,
Rua varchar(60),
cep varchar(9),
email varchar(50),
estadoCadastro bit,
senha varchar(50),
constraint fk_gerente_cidade foreign key(idCidade) references Cidade(id)
);

create table Professor(
id integer auto_increment primary key,
nome varchar(50),
cpf varchar(14),
dataNascimento date,
idCidade integer,
Rua varchar(60),
cep varchar(9),
email varchar(50),
estadoCadastro bit,
departamento varchar(30),
AreaEstudo varchar(50),
constraint fk_professor_cidade foreign key(idCidade) references Cidade(id)
);

create table Nutricionista(
id integer auto_increment primary key,
nome varchar(50),
cpf varchar(14),
dataNascimento date,
idCidade integer,
Rua varchar(60),
cep varchar(9),
email varchar(50),
estadoCadastro bit,
senha varchar(50),
crn varchar(15),
constraint fk_nutricionista_cidade foreign key(idCidade) references Cidade(id)
);

create table Servidor(
id integer auto_increment primary key,
nome varchar(50),
cpf varchar(14),
dataNascimento date,
idCidade integer,
Rua varchar(60),
cep varchar(9),
email varchar(50),
estadoCadastro bit,
dataIngresso date,
unidade varchar(50),
constraint fk_servidor_cidade foreign key(idCidade) references Cidade(id)
);

create table Janta(
id integer auto_increment primary key,
idArroz integer,
idFeijao integer,
idAcompanhamento integer,
idSalada integer,
idSobremesa integer,
qtdeArroz double,
qtdeAcompanhamento double,
qtdeSalada double,
qtdeSobremesa double,
constraint fk_janta_arroz foreign key(idArroz) references Ingrediente(id),
constraint fk_janta_feijao foreign key(idFeijao) references Ingrediente(id),
constraint fk_janta_acompanhamento foreign key(idAcompanhamento) references Ingrediente(id),
constraint fk_janta_salada foreign key(idSalada) references Ingrediente(id),
constraint fk_janta_sobremesa foreign key(idSobremesa) references Ingrediente(id)
);

create table Almoco(
id integer auto_increment primary key,
idArroz integer,
idFeijao integer,
idAcompanhamento integer,
idSalada integer,
idSobremesa integer,
qtdeArroz double,
qtdeAcompanhamento double,
qtdeSalada double,
qtdeSobremesa double,
constraint fk_almoco_arroz foreign key(idArroz) references Ingrediente(id),
constraint fk_almoco_feijao foreign key(idFeijao) references Ingrediente(id),
constraint fk_almoco_acompanhamento foreign key(idAcompanhamento) references Ingrediente(id),
constraint fk_almoco_salada foreign key(idSalada) references Ingrediente(id),
constraint fk_almoco_sobremesa foreign key(idSobremesa) references Ingrediente(id)
);

create table Cardapio(
id integer auto_increment primary key,
idAlmoco integer,
idJanta integer,
idNutricionista integer,
constraint fk_cardapio_almoco foreign key(idAlmoco) references Almoco(id),
constraint fk_cardapio_janta foreign key(idJanta) references Janta(id),
constraint fk_cardapio_nutricionista foreign key(idNutricionista) references Nutricionista(id)
);

create table Registro(
id integer auto_increment primary key,
cpf varchar(14),
dataHora datetime,
valorCobrado double,
justificativa varchar(50),
idAtendente integer,
idCardapio integer,
constraint fk_registro_atendente foreign key(idAtendente) references Atendente(id),
constraint fk_registro_cardapio foreign key(idCardapio) references Cardapio(id)
);

ALTER TABLE Registro add column idRegistro integer; 

ALTER TABLE Registro add constraint fk_tipoPessoa_registro foreign key (idRegistro) references TipoPessoa(id); 

UPDATE Registro set idRegistro=1 where id = 1;
UPDATE Registro set idRegistro=1 where id = 2;
UPDATE Registro set idRegistro=1 where id = 3;
UPDATE Registro set idRegistro=1 where id = 4;
UPDATE Registro set idRegistro=7 where id = 5;
UPDATE Registro set idRegistro=7 where id = 6;
UPDATE Registro set idRegistro=5 where id = 7;
UPDATE Registro set idRegistro=5 where id = 8;
UPDATE Registro set idRegistro=1 where id = 9;
UPDATE Registro set idRegistro=7 where id = 10;
UPDATE Registro set idRegistro=7 where id = 11;
UPDATE Registro set idRegistro=7 where id = 12;
UPDATE Registro set idRegistro=1 where id = 13;
UPDATE Registro set idRegistro=1 where id = 14;
UPDATE Registro set idRegistro=1 where id = 15;
UPDATE Registro set idRegistro=1 where id = 16;
UPDATE Registro set idRegistro=7 where id = 17;
UPDATE Registro set idRegistro=7 where id = 18;
UPDATE Registro set idRegistro=5 where id = 19;
UPDATE Registro set idRegistro=5 where id = 20;
UPDATE Registro set idRegistro=1 where id = 21;
UPDATE Registro set idRegistro=7 where id = 22;
UPDATE Registro set idRegistro=7 where id = 23;
UPDATE Registro set idRegistro=7 where id = 24;

INSERT INTO TipoIngrediente(nome) VALUES ("Arroz"),("Feijao"),("Carne"),("Acompanhamento"),
("Suco"),("Vegano"),("Macarrão"),("Sobremesa"),("Salada"),("Fruta");

INSERT INTO Ingrediente(nome,idTipoIngrediente) VALUES
("Arroz Branco",1),("Arroz Integral",1),("Feijão Preto",1),("Feijão Fradinho",2),("Feijão Branco",2),
("Carne de Gado",3),("Carne de Frango",3),("Carne de Porco",3),("Peixe",3),("Grão de Bico",4),
("Banana Cozida",4),("Pure de Batata",4),("Pure de Abobora",4),("Casca de Banana Frita",6),("Risoto Vegano",6),
("Macarrao Pena",7),("Macarrao Vegetariano",7),("Inholine",7),("Pudim",8),("Gelatina",8),
("Sagu",8),("Repolho",9),("Cenoura e Beterraba ralada",9),("Alface e Tomate",9),("Agrião",9),
("Maça",10),("Laranja",10),("Melancia",10);

INSERT INTO Estado(nome) values ("Rio Grande do Sul"),("Parana"),("Santa Catarina"),("São Paulo"),
("Rio de Janeiro"),("Pernambuco"),("Minas Gerais"),("Alagoas"),("Acre"),("Distrito Federal"),("Mato Grosso do Sul"),("Espirito Santo");

INSERT INTO Cidade(nome,idEstado) values ("Curitibanos",1),("Rio Grande do Sul",1),("Pelotas",1),("Curitiba",2),("Morretes",2),("Paranaguá",2),
("Florianopolis",3),("Bom Jesus",3),("Xanxerê",3),("São Paulo",4),("Campinas",4),("Hortolândia",4),("Rio de Janeiro",5),("Paraty",5),("Ilha Grande",5),
("Recife",6),("Olinda",6),("Porto de Galinhas",6),("Tiradentes",7),("Belo Horizonte",7),("Ouro Preto",7),("Maceió",8),("Maragogi",8),("Ilha de Santo Aleixo",8),
("Rio Branco",9),("Cidade Oeste",9),("Nova Crença",9),("Brasilia",10),("Angra",10),("Sandy",10),("Bonito",11),("Milane",11),("Corumbá",11),
("Cidade nova",12),("Primavera do Oeste",12),("Aparecida",12);

INSERT INTO Atendente(nome,cpf,dataNascimento,idCidade,Rua,cep,email,estadoCadastro,senha) VALUES
("Gustavo Achinitz","122.967.220-67","1997-03-01",4,"Rua da Paz","82960-020","gustavo@gmail.com",1,"1234"),
("Laerte Souza Neto","153.055.250-80","1995-05-06",2,"Rua da Paciencia","82960-010","laerte@gmail.com",1,"1234"),
("Renan Nelsen","562.266.360-17","1995-03-02",7,"Rua da Meditacao","82960-030","renan@gmail.com",1,"1234"),
("Felipe Moreira","117.296.470-06","1996-01-01",8,"Rua da Bondade","82960-040","felipe@gmail.com",1,"1234"),
("Douglas de Oliveira","544.072.270-01","1985-03-23",10,"Rua de lá pra cá","82960-050","douglas@gmail.com",1,"1234");

INSERT INTO Aluno(nome,cpf,dataNascimento,idCidade,Rua,cep,email,estadoCadastro,AnoIngresso,grr) VALUES
("Pedro da Silva","528.265.780-57","1997-03-01",4,"Rua da Paz","82960-020","pedro@ufpr.com",1,"2017-02-01","20170101"),
("Juarez Schimit","198.648.990-66","1995-05-06",2,"Rua da Paciencia","82960-010","juarez@ufpr.com",1,"2017-02-01","20170102"),
("Jeroniza Pereira","486.459.100-80","1995-03-02",7,"Rua da Meditacao","82960-030","jeroniza@ufpr.com",1,"2018-07-01","20180701"),
("Geni Gonçalves","910.916.690-40","1996-01-01",8,"Rua da Bondade","82960-040","geni@ufpr.com",1,"2018-02-01","20180101"),
("Alice Conceição","810.527.650-09","1985-03-23",10,"Rua de lá pra cá","82960-050","alice@ufpr.com",1,"2018-02-01","20180102");

INSERT INTO Externo(nome,cpf,dataNascimento,idCidade,Rua,cep,email,estadoCadastro,Observacao) VALUES
("Antonio Gonçalves","664.982.740-18","1987-03-01",4,"Rua da Paz","82960-020","antonio@email.com",1,"Pai do estudante"),
("Abair de Oliveira","441.296.140-02","1965-05-06",2,"Rua da Paciencia","82960-010","abair@bol.com",1,"Mae do estudante"),
("Alessandra Leite","014.859.860-95","1985-03-02",7,"Rua da Meditacao","82960-030","alessandra@uol.com",1,"Irmã da estudante"),
("Helena Leite","047.561.610-30","1972-01-12",8,"Rua da Bondade","82960-040","helena@yahoo.com",1,"Prima da estudante"),
("Mayara Coutinho","134.518.780-70","1970-03-29",10,"Rua de lá pra cá","82960-050","mayara@hotmail.com",1,"Estava sem trocado");

INSERT INTO Gerente(nome,cpf,dataNascimento,idCidade,Rua,cep,email,estadoCadastro,senha) VALUES
("Luis Antonio Neves","629.242.390-52","1950-03-01",4,"Rua da Paz","82960-020","neves@ufpr.com",1,"1234"),
("Hilary Clinton","420.981.550-00","1995-05-06",2,"Rua da Paciencia","82960-010","hilaryImportada@ufpr.com",1,"1234"),
("Michael Jackson","633.425.820-65","1995-03-02",7,"Rua da Meditacao","82960-030","michaelMorreu@ufpr.com",1,"1234"),
("Tifani Kardasha","146.983.080-97","1996-01-01",8,"Rua da Bondade","82960-040","tifaniFedida@ufpr.com",1,"1234"),
("Dilma Roussef","815.952.350-51","1985-03-23",10,"Rua de lá pra cá","82960-050","dilminha@ufpr.com",1,"1234");

INSERT INTO Nutricionista(nome,cpf,dataNascimento,idCidade,Rua,cep,email,estadoCadastro,senha,crn) VALUES
("Frozen Gelada","660.835.330-82","1950-03-01",4,"Rua da Paz","82960-020","lerigo@ufpr.com",1,"1234","CRN123-1"),
("Masha sem Urso","202.999.290-94","1995-05-06",2,"Rua da Paciencia","82960-010","mtfmasha@ufpr.com",1,"1234","CRN124-2"),
("Claudinho sem buchecha","729.445.160-51","1995-03-02",7,"Rua da Meditacao","82960-030","assimsemvoce@ufpr.com",1,"1234","CRN125-3"),
("Fresno e lulu Santos","926.891.230-96","1996-01-01",8,"Rua da Bondade","82960-040","maiorqueasmuralhas@ufpr.com",1,"1234","CRN126-4"),
("Ivete sem galo","215.455.100-91","1985-03-23",10,"Rua de lá pra cá","82960-050","todomundovai@ufpr.com",1,"1234","CRN127-5");

INSERT INTO Professor(nome,cpf,dataNascimento,idCidade,Rua,cep,email,estadoCadastro,departamento,AreaEstudo) VALUES
("Luis Felipe Galdanha","629.242.390-52","1950-03-01",4,"Rua da Paz","82960-020","saldanhagaldanhaxd@ufpr.com",1,"DTI","Banco de Dados"),
("Luiz Lopes Lemos","420.981.550-00","1995-05-06",2,"Rua da Paciencia","82960-010","ohgarotinho@ufpr.com",1,"CAPE","Comportamento Humano"),
("Jean Pinho","633.425.820-65","1995-03-02",7,"Rua da Meditacao","82960-030","gauchobah@ufpr.com",1,"CAGE","Estagio"),
("Silvana Piov","146.983.080-97","1996-01-01",8,"Rua da Bondade","82960-040","silvana@ufpr.com",1,"DTI","Inteligencia Artificial"),
("Sandramara da Silva","815.952.350-51","1985-03-23",10,"Rua de lá pra cá","82960-050","sandramara@ufpr.com",1,"CAPE","Relações Humanas");

INSERT INTO Servidor(nome,cpf,dataNascimento,idCidade,Rua,cep,email,estadoCadastro,dataIngresso,unidade) VALUES
("Joao Gomes","302.994.040-30","1950-03-01",4,"Rua da Paz","82960-020","vozestranha@ufpr.com",1,"2000-03-01","Centro"),
("Gustavo Pereira","580.258.160-36","1995-05-06",2,"Rua da Paciencia","82960-010","pereirao@ufpr.com",1,"2001-11-11","Centro"),
("Esteban Tavares","445.787.580-52","1995-03-02",7,"Rua da Meditacao","82960-030","sophiabylucas@ufpr.com",1,"2008-12-01","Botanico"),
("Joey Jhordson","587.920.720-03","1996-01-01",8,"Rua da Bondade","82960-040","deadmemories@ufpr.com",1,"1998-03-25","Casa Branca"),
("Jhonny Cash","643.840.510-85","1985-03-23",10,"Rua de lá pra cá","82960-050","comesarround@ufpr.com",1,"2006-07-08","SEPT");

INSERT INTO Janta(id,idArroz,idFeijao,idAcompanhamento,idSalada,idSobremesa,qtdeArroz,qtdeAcompanhamento,qtdeSalada,qtdeSobremesa) VALUES (6,1,3,10,22,21,4.5,1.6,2.0,1.5),
(1,2,3,11,23,20,2.5,1.2,2.4,1.7),(2,1,4,6,22,19,2.5,1.6,2.0,1.5),(3,1,5,7,25,21,4.5,1.6,2.0,1.5),(4,2,5,8,24,20,3.6,2.6,1.1,2.5),(5,2,4,6,23,19,2.5,3.6,6.0,1.5);

INSERT INTO Almoco(id,idArroz,idFeijao,idAcompanhamento,idSalada,idSobremesa,qtdeArroz,qtdeAcompanhamento,qtdeSalada,qtdeSobremesa) VALUES (1,1,3,10,22,21,4.5,1.6,2.0,1.5),
(2,2,3,11,23,20,2.5,1.2,2.4,1.7),(3,1,4,6,22,19,2.5,1.6,2.0,1.5),(4,1,5,7,25,21,4.5,1.6,2.0,1.5),(5,2,5,8,24,20,3.6,2.6,1.1,2.5),(6,2,4,6,23,19,2.5,3.6,6.0,1.5);

INSERT INTO Cardapio(id,idAlmoco,idJanta,idNutricionista) VALUES (1,1,2,1),(2,2,1,1),(3,2,1,2),(4,3,3,3),(5,2,1,4),(6,2,3,5);

INSERT INTO Registro(cpf,dataHora,valorCobrado,justificativa,idAtendente,idCardapio) VALUES 
("528.265.780-57",'2018-02-01 12:30:45',1.30,"Estava com fome",1,1),
("198.648.990-66",'2018-02-01 12:32:33',1.30,"Estava com fome",1,1),
("910.916.690-40",'2018-02-01 12:33:56',1.30,"Estava com fome",1,1),
("810.527.650-09",'2018-02-01 12:42:12',1.30,"Estava com fome",4,1),
("302.994.040-30",'2018-02-01 12:50:23',1.30,"Estava com fome",4,1),
("445.787.580-52",'2018-02-01 12:51:47',1.30,"Estava com fome",1,1),
("629.242.390-52",'2018-02-01 14:30:45',1.30,"Estava com fome",1,1),
("420.981.550-00",'2018-02-01 18:32:33',1.30,"Estava com fome",3,1),
("910.916.690-40",'2018-02-01 18:33:56',1.30,"Estava com fome",3,1),
("445.787.580-52",'2018-02-01 19:42:12',1.30,"Estava com fome",2,1),
("302.994.040-30",'2018-02-01 19:50:23',1.30,"Estava com fome",2,1),
("643.840.510-85",'2018-02-01 19:51:47',1.30,"Estava com fome",1,1);

select * from Atendente;
select * from Servidor;
select * from Nutricionista;
select * from Professor;
select * from Gerente;
select * from Externo;
select * from Servidor;
select * from Registro;
select * from Cardapio;
select * from Janta;
select * from Almoco;
select * from TipoIngrediente;
select * from Ingrediente;
select * from Estado;
select * from Cidade;

/*Relatório um :(*/
select 'Almoço',DAY(dataHora) as 'Dia',concat('R$ ',replace(format(SUM(valorCobrado),2),'.',',')) as 'Total',format(SUM(valorCobrado),2) from Registro where MONTH(dataHora) = 2 and TIME(dataHora)<= '17:00:00'
union
select 'Janta',DAY(dataHora) as 'Dia',concat('R$ ',replace(format(SUM(valorCobrado),2),'.',',')) as 'Total',format(SUM(valorCobrado),2) from Registro where MONTH(dataHora) = 2 and TIME(dataHora)>= '17:00:00';

/*Relatório dois :(*/
select 'Almoço',MONTH(dataHora) as 'Mes',concat('R$ ',replace(format(SUM(valorCobrado),2),'.',',')) as 'Total' from Registro where YEAR(dataHora) = 2018 and TIME(dataHora)<= '17:00:00' group by MONTH(dataHora)
union
select 'Janta',MONTH(dataHora) as 'Mes',concat('R$ ',replace(format(SUM(valorCobrado),2),'.',',')) as 'Total' from Registro where YEAR(dataHora) = 2018 and TIME(dataHora)>= '17:00:00' group by MONTH(dataHora);

select * from Registro;

/*Relatório três*/
SELECT CASE WHEN TIME(dataHora) <= '17:00:00' THEN 'Almoço' ELSE 'Janta' END AS 'Periodo',
DAY(dataHora) AS 'Dia',MONTHNAME(dataHora) AS 'Mes',TIME(dataHora),FORMAT(SUM(valorCobrado),2) as 'total',concat('R$ ',REPLACE(FORMAT(SUM(valorCobrado),2),'.',',')) AS 'Total' 
FROM Registro WHERE cpf='198.648.990-66' AND DATE(dataHora) BETWEEN '2017-05-03' AND '2020-10-02' GROUP BY dataHora;