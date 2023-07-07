CREATE TABLE usuarios
(
    id            SERIAL       NOT NULL,
    login varchar(100) not null,
    senha varchar(255) not null,

    CONSTRAINT pk_usuario
        PRIMARY KEY (id)
);