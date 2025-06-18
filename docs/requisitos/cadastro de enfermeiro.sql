CREATE TABLE enfermeiros (
    nome VARCHAR(100) NOT NULL,
    cpf VARCHAR(14) NOT NULL UNIQUE, 
    turno VARCHAR(20) NOT NULL
    primary key (nome)
);