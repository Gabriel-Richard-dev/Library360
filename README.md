# Library360

Sistema de catálogo de livros e autores em arquitetura de microsserviços.

## Como rodar

```bash
docker compose up --build
```

Depois é só acessar **http://localhost**.

## Estrutura

- **front-ms** — frontend (Spring Boot + Thymeleaf) que consome os outros serviços
- **autor-ms** — microsserviço de autores, com seu próprio banco (`autor-db`)
- **livro-ms** — microsserviço de livros, com seu próprio banco (`livro-db`)
- **nginx** — proxy reverso na porta 80, que roteia:
  - `/` → front-ms
  - `/api/autor` → autor-ms
  - `/api/livro` → livro-ms

Cada microsserviço é uma aplicação Spring Boot com banco PostgreSQL próprio.
