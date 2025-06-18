CREATE TABLE alas (
    descricao varchar(40) NOT NULL,
    nome VARCHAR(50) NOT NULL UNIQUE,
    andar INT NOT NULL,
   PRIMARY KEY (descricao)
    
);