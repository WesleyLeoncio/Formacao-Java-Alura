CREATE TABLE consultas
(
    id            SERIAL       NOT NULL,
    medico_id     INTEGER      NOT NULL,
    paciente_id   INTEGER      NOT NULL,
    data          DATETIME     NOT NULL,

    CONSTRAINT pk_consulta
      PRIMARY KEY (id),
    CONSTRAINT fk_consulta_medico_id
      FOREIGN KEY(medico_id)
        REFERENCES medicos(id),
    CONSTRAINT fk_consulta_paciente_id
      FOREIGN KEY(paciente_id)
        REFERENCES pacientes(id)
);