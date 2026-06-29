create table autor (
    id BIGSERIAL PRIMARY KEY,
    nome varchar(100) not null,
    nacionalidade varchar(80),
    anoNascimento int not null,
    created_at timestamp not null default now(),
    updated_at timestamp not null default now()
);