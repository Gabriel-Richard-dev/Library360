CREATE TABLE livro(
    id BIGSERIAL PRIMARY KEY,
    titulo VARCHAR(150) not null,
    genero VARCHAR(80) not null,
    ano_publicacao int not null,
    autor_id BIGINT not null,
    disponivel boolean NOT NULL default true,
    created_at timestamp default now(),
    updated_at timestamp default now()
);