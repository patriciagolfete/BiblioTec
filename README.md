
<img width="1536" height="1024" alt="image" src="https://github.com/user-attachments/assets/de628e7c-a980-46ba-9a4e-42746d2cb16c" /><div align="center">

# 📚 BiblioTec

<img src="logo.png" width="260"/>

### Sistema de Gerenciamento de Biblioteca

**Disciplina:** Programação Orientada a Objetos II
**Curso:** Engenharia de Software
**Instituição:** UTFPR – Universidade Tecnológica Federal do Paraná

---

### 👩‍💻 Integrantes

[**Patricia Lacerda Golfete**](https://github.com/patriciagolfete)

[**Maria Vitória Mendes Storel**](https://github.com/m4riavit0ria)

</div>

---

## 📖 Sobre o Projeto

O **BiblioTec** é um sistema de gerenciamento de biblioteca desenvolvido com o objetivo de auxiliar no controle de livros, usuários, empréstimos e devoluções, proporcionando maior organização e automação dos processos administrativos.

O sistema foi desenvolvido utilizando **Java + Spring Boot**, com persistência de dados em **PostgreSQL**, seguindo conceitos de **Programação Orientada a Objetos (POO)** e arquitetura **MVC (Model-View-Controller)**.

---

## 🚀 Tecnologias Utilizadas

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge\&logo=openjdk\&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge\&logo=springboot\&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge\&logo=postgresql\&logoColor=white)
![Hibernate](https://img.shields.io/badge/Hibernate-59666C?style=for-the-badge\&logo=hibernate\&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge\&logo=apachemaven\&logoColor=white)
![HTML5](https://img.shields.io/badge/HTML-E34F26?style=for-the-badge\&logo=html5\&logoColor=white)
![CSS3](https://img.shields.io/badge/CSS-1572B6?style=for-the-badge\&logo=css3\&logoColor=white)
![JavaScript](https://img.shields.io/badge/JavaScript-F7DF1E?style=for-the-badge\&logo=javascript\&logoColor=black)

---

## 📌 Funcionalidades

### 👤 Usuários

✔ Cadastro de usuários
✔ Consulta de usuários
✔ Alteração de usuários
✔ Exclusão de usuários
✔ Busca de usuários por nome
✔ Validação para impedir cadastro duplicado por e-mail

### 📚 Livros

✔ Cadastro de livros
✔ Consulta de livros
✔ Alteração de livros
✔ Exclusão de livros
✔ Ordenação por autor
✔ Ordenação por editora
✔ Consulta de livros disponíveis
✔ Consulta de livros emprestados

### 🔄 Empréstimos

✔ Realização de empréstimos
✔ Controle de disponibilidade do livro
✔ Devolução de livros
✔ Atualização automática do status do empréstimo
✔ Controle da quantidade de empréstimos do usuário

### 🔐 Administrador

✔ Cadastro de administrador
✔ Login do administrador
✔ Validação de login duplicado

---

## 🏗️ Arquitetura do Projeto

O sistema foi desenvolvido seguindo o padrão **MVC (Model-View-Controller)**.

```text
src/main/java/com/bibliotec/bibliotec_api

├── controller
├── model
├── repository
├── config
```

---

## 🗄️ Banco de Dados

Banco de dados utilizado:

```text
PostgreSQL
```

Tabelas principais:

```text
administrador
usuario
livro
emprestimo
```

---

## ▶️ Como Executar o Projeto

### 1. Clonar o repositório

```bash
git clone LINK_DO_REPOSITORIO
```

### 2. Configurar PostgreSQL

Criar um banco chamado:

```text
bibliotec
```

Configurar as credenciais no arquivo:

```text
application.properties
```

### 3. Executar a aplicação

Executar a classe:

```text
BibliotecApiApplication.java
```

A aplicação ficará disponível em:

```text
http://localhost:8082
```

---

## 🔗 Principais Endpoints

### Usuários

```http
GET /usuarios
POST /usuarios
PUT /usuarios/{id}
DELETE /usuarios/{id}
GET /usuarios/buscar?nome=
```

### Livros

```http
GET /livros
POST /livros
PUT /livros/{id}
DELETE /livros/{id}
GET /livros/ordenar/autor
GET /livros/ordenar/editora
GET /livros/disponiveis
GET /livros/emprestados
```

### Empréstimos

```http
GET /emprestimos
POST /emprestimos
PUT /emprestimos/{id}/devolver
```

### Administrador

```http
POST /administradores
POST /administradores/login
```

---

<div align="center">

### 📚 BiblioTec

**Tecnologia para conectar conhecimento**

</div>
