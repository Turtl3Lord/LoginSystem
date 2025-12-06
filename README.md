````md
# 🚀 LoginSystem — Backend (Spring Boot) + Frontend (HTML/CSS/JS)

Sistema completo de autenticação utilizando **Spring Boot**, **JWT** e **PostgreSQL**.

O projeto segue boas práticas de arquitetura, separando claramente as camadas.

---

## 📌 Visão Geral

O **LoginSystem** é uma API de autenticação que oferece:

- Registro de usuários  
- Login com JWT  
- Exclusão de conta  
- Retorno de perfil autenticado  
- Persistência com PostgreSQL  
- Arquitetura limpa e modular  

Também acompanha um front-end básico para interação com a API.

---

## 🧱 Arquitetura

Estrutura baseada em responsabilidades:

- `controllers/` → Endpoints REST  
- `services/` → Regras de negócio  
- `DTO/` → Objetos de transferência de dados  
- `models/` → Entidades do banco  
- `exceptions/` → Tratamento de erros  
- `config/` → Configurações (CORS, JWT, etc.)

---

## 🛠️ Tecnologias Utilizadas

### Back-end
- Java + Spring Boot  
- Spring Web  
- Spring Data JPA / Hibernate  
- Jakarta Validation  
- JWT (JSON Web Token)  
- PostgreSQL  
- Maven  

### Front-end
- HTML5  
- CSS3  
- JavaScript (Vanilla)

---

## 🧩 Configuração do Ambiente

### Banco de Dados (PostgreSQL)

Criação do banco de dados:
entre no shell do postgresql instalado na sua máquina e rode o comando
CREATE DATABASE LoginSystem;


## ⚙️ Variáveis de Ambiente (`application.properties`)

```properties
spring.application.name=BackEnd

# JWT
JWT_SECRET_KEY=${_JWT_SECRET_KEY:abaracatedebarabanana}
JWT_EXPIRATION_SECONDS=${_JWT_EXPIRATION_SECONDS:3600}
JWT_ISSUER=${_JWT_ISSUER:http://localhost:5000}

# Porta do servidor
server.port=${PORT:5000}

# Banco de Dados
spring.datasource.url=${DB_URL:jdbc:postgresql://localhost:5432/LoginSystem}
spring.datasource.username=${DB_USERNAME:seu_usuário}
spring.datasource.password=${DB_PASSWORD:sua_senha}
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=update

# CORS
frontend.url=${FRONTEND_URL:http://seu-endereco-de-frontend}


---

## ▶️ Como Executar o Back-end

iniciar o main em BackEnd>src>main>java>com>BackEnd>BackEndApplication.java

A API ficará disponível em:

bash
http://localhost:5000


---

## 🔌 Endpoints da API

| Método | Rota             | Descrição                |
| ------ | ---------------- | ------------------------ |
| POST   | `api/auth/local/signup` | Registrar novo usuário   |
| POST   | `api/auth/local/signin`    | Login e geração de token |
| DELETE | `api/auth/local/delete`   | Excluir conta do usuário |
| GET    | `api/user/profile`  | Obter perfil autenticado |

---

## 💻 Front-end

O front-end é composto por páginas simples que consomem a API utilizando **fetch/AJAX** com **JavaScript puro**.

Basta abrir o arquivo `index.html` no navegador.

---

## ✅ Funcionalidades

* Autenticação via JWT
* Hash de senha seguro
* Integração com PostgreSQL
* Validação de dados via DTOs
* Tratamento centralizado de erros
* CORS configurado

---

## 📄 Licença

Projeto de uso livre para fins educacionais e comerciais.

```
```
