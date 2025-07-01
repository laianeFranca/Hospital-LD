CREATE TABLE alas (
    descricao varchar(40) NOT NULL,
    nome VARCHAR(50) NOT NULL UNIQUE,
    andar INT NOT NULL,
   PRIMARY KEY (descricao)
    
);

CREATE TABLE enfermeiros (
    nome VARCHAR(100) NOT NULL,
    cpf VARCHAR(14) NOT NULL UNIQUE, 
    turno VARCHAR(20) NOT NULL
    primary key (nome)
);

CREATE TABLE leitos (
    codigo VARCHAR(20) NOT NULL UNIQUE,
    numero INT NOT NULL,
    tipo VARCHAR(30) NOT NULL,
    primary key (codigo)
);