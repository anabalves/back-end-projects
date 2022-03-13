create table ocorrencia
(
    id            BIGINT generated by default as identity NOT NULL,
    entrega_id    BIGINT                                  NOT NULL,
    descricao     TEXT                                    NOT NULL,
    data_registro TIMESTAMP                               NOT NULL,

    primary key (id)
);

alter table ocorrencia
    add constraint fk_ocorrencia_entrega
        foreign key (entrega_id) references entrega (id);