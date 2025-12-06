# 🚀 LoginSystem — Backend em Spring Boot + Frontend HTML/CSS/JS

Sistema completo de autenticação com **Spring Boot**, **JWT** e **PostgreSQL**, acompanhado de um front-end simples desenvolvido com **HTML, CSS e JavaScript**.  
O projeto segue boas práticas de arquitetura e separação de responsabilidades, utilizando Controllers, Services, DTOs, Models e Exceptions.

---

## 📚 Sumário
- [Visão Geral](#visão-geral)
- [Arquitetura](#arquitetura)
- [Tecnologias Utilizadas](#tecnologias-utilizadas)
- [Estrutura do Projeto](#estrutura-do-projeto)
- [Configuração do Ambiente](#configuração-do-ambiente)
- [Variáveis de Ambiente](#variáveis-de-ambiente)
- [Como Executar o Back-end](#como-executar-o-back-end)
- [Endpoints da API](#endpoints-da-api)
- [Front-end](#front-end)
- [Funcionalidades](#funcionalidades)
- [Licença](#licença)

---

## 📌 Visão Geral

O **LoginSystem** é uma API de autenticação que oferece:

- Registro de usuários  
- Login com JWT  
- Exclusão de conta  
- Retorno de perfil autenticado  
- Integração total com PostgreSQL  
- Arquitetura limpa e modular  

Ele inclui também um front-end básico para interação com a API.

---

## 🧱 Arquitetura

O back-end segue uma estrutura organizada por responsabilidade:

- **controllers/** → Endpoints REST  
- **services/** → Regras de negócio  
- **DTO/** → Objetos de transferência de dados  
- **models/** → Entidades do banco  
- **exceptions/** → Tratamento de erros  
- **config/** → Configurações gerais (CORS, JWT etc.)

---

## 🛠️ Tecnologias Utilizadas

### Back-end
- Java + Spring Boot  
- Spring Web  
- Spring JPA / Hibernate  
- Jakarta Validation  
- JWT (Json Web Token)  
- PostgreSQL  
- Maven  

### Front-end
- HTML5  
- CSS3  
- JavaScript (Vanilla)

---

## 🧩 Configuração do Ambiente

### Banco de Dados — PostgreSQL

Crie o banco:

```sql
CREATE DATABASE LoginSystem;
```
### Application.properties
spring.application.name=BackEnd

# JWT
JWT_SECRET_KEY=${_JWT_SECRET_KEY:abaracatedebarabanana}
JWT_EXPIRATION_SECONDS=${_JWT_EXPIRATION_SECONDS:3600}
JWT_ISSUER=${_JWT_ISSUER:http://localhost:5000}

# Porta do servidor
server.port=${PORT:5000}

# Banco de Dados
spring.datasource.url=${DB_URL:jdbc:postgresql://localhost:5432/LoginSystem}
spring.datasource.username=${DB_USERNAME:postgres}
spring.datasource.password=${DB_PASSWORD:admin}
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect

spring.jpa.hibernate.ddl-auto=update

# CORS
frontend.url=${FRONTEND_URL:http://endereço da sua url}

