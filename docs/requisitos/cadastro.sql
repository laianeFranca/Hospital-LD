CREATE TABLE leitos (
    codigo VARCHAR(20) NOT NULL UNIQUE,
    numero INT NOT NULL,
    tipo VARCHAR(30) NOT NULL,
primary key (codigo)
);