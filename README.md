# API de Autenticação com Spring Boot, JWT e Roles

![Java](https://img.shields.io/badge/Java-21-blue?logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.X.X-green?logo=spring&logoColor=white)
![Spring Security](https://img.shields.io/badge/Spring_Security-6.X.X-blueviolet?logo=spring&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-JSON_Web_Token-orange?logo=jsonwebtokens&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-8.0-blue?logo=mysql&logoColor=white)

## 📖 Sobre o Projeto

Este projeto consiste em uma API RESTful desenvolvida em **Java** com o framework **Spring Boot**. O objetivo principal é demonstrar um sistema de autenticação e autorização robusto, utilizando **JSON Web Tokens (JWT)** para proteger os endpoints e controle de acesso baseado em papéis de usuário (**Role-Based Access Control**).

A aplicação define dois tipos de papéis (`Role`): `USER` e `ADMIN`, cada um com permissões distintas, seguindo as melhores práticas de segurança para APIs modernas.

## ✨ Funcionalidades Principais

-   **Cadastro de Usuários**: Permite o registro de novos usuários com nome, e-mail, senha e um papel (`role`) associado.
-   **Autenticação com JWT**: Sistema de login que, em caso de sucesso, gera um token JWT para o usuário.
-   **Controle de Acesso por Papel (Role)**:
    -   Endpoints públicos, como registro e login.
    -   Endpoints que exigem apenas autenticação (acessíveis por `USER` e `ADMIN`).
    -   Endpoints restritos que exigem autorização específica (acessíveis apenas por `ADMIN`).
-   **Gerenciamento de Usuários**:
    -   `USER`: Pode visualizar e editar seu próprio perfil.
    -   `ADMIN`: Possui controle total (CRUD) sobre todos os usuários do sistema.

## 🛠️ Tecnologias Utilizadas

-   **Java 21**
-   **Spring Boot 3**
-   **Spring Security 6**
-   **Spring Data JPA**
-   **JSON Web Token (JWT)** - biblioteca `io.jsonwebtoken`
-   **MySQL 8** - Banco de dados relacional
-   **Maven** - Gerenciador de dependências e build
-   **Lombok** - Para redução de código boilerplate

## ⚙️ Pré-requisitos

Antes de começar, você vai precisar ter instalado em sua máquina:
* [JDK 21](https://www.oracle.com/java/technologies/downloads/#java21) ou superior.
* [Maven 3.8](https://maven.apache.org/download.cgi) ou superior.
* [MySQL Server 8.0](https://dev.mysql.com/downloads/mysql/) ou um contêiner Docker com MySQL.
* [Git](https://git-scm.com/) para versionamento do código.
* Uma ferramenta de API, como o [Postman](https://www.postman.com/downloads/), para testar os endpoints.

## 🚀 Instalação e Execução

Siga os passos abaixo para executar o projeto localmente:

1.  **Clone o repositório:**
    ```bash
    git clone https://github.com/murilotoniol/api-autenticacao
    cd seu-repositorio
    ```

2.  **Configure o Banco de Dados:**
    * Crie um banco de dados no seu MySQL chamado `auth_db`.
    * Abra o arquivo `src/main/resources/application.properties`.
    * Altere as propriedades `spring.datasource.username` e `spring.datasource.password` com suas credenciais do MySQL.
        ```properties
        spring.datasource.url=jdbc:mysql://localhost:3306/auth_db
        spring.datasource.username=seu_usuario_mysql
        spring.datasource.password=sua_senha_mysql
        ```

3.  **Execute a Aplicação:**
    * Abra um terminal na raiz do projeto e execute o seguinte comando Maven:
        ```bash
        mvn spring-boot:run
        ```
    * A API estará disponível em `http://localhost:5757` (ou a porta que você configurou).

## Endpoints da API

Aqui está a documentação de todos os endpoints disponíveis na aplicação.

### 🔑 Autenticação (`/api/auth`)

| Funcionalidade          | Método HTTP | Endpoint              | Autorização | Corpo da Requisição (Exemplo)                                                                              | Resposta de Sucesso (200 OK)                               |
| ----------------------- | ----------- | --------------------- | ----------- | ---------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------- |
| **Registrar Usuário** | `POST`      | `/api/auth/register`  | **Público** | `{"name":"Nome","email":"email@exemplo.com","password":"senha","role":"USER"}` (role é opcional, padrão USER) | `{"token": "seu.jwt.token"}`                               |
| **Login de Usuário** | `POST`      | `/api/auth/login`     | **Público** | `{"email":"email@exemplo.com","password":"senha"}`                                                           | `{"token": "seu.jwt.token"}`                               |

### 👤 Usuário Autenticado (`/api/users`)

| Funcionalidade           | Método HTTP | Endpoint        | Autorização                    | Corpo da Requisição (Exemplo)          | Resposta de Sucesso (200 OK)                   |
| ------------------------ | ----------- | --------------- | ------------------------------ | -------------------------------------- | ---------------------------------------------- |
| **Ver Próprio Perfil** | `GET`       | `/api/users/me` | **Autenticado** (USER ou ADMIN) | (Nenhum)                               | `{"id":1,"name":"...","email":"...","role":"..."}` |
| **Editar Próprio Perfil**| `PUT`       | `/api/users/me` | **Autenticado** (USER ou ADMIN) | `{"name":"Novo Nome do Usuário"}`      | `{"id":1,"name":"Novo Nome...","email":"..."}`  |

### 🛡️ Administração (`/api/admin`)

| Funcionalidade               | Método HTTP | Endpoint              | Autorização      | Corpo da Requisição (Exemplo)                                                    | Resposta de Sucesso                                    |
| ---------------------------- | ----------- | --------------------- | ---------------- | -------------------------------------------------------------------------------- | ------------------------------------------------------ |
| **Listar Todos os Usuários** | `GET`       | `/api/admin/users`    | **ADMIN** | (Nenhum)                                                                         | `200 OK` - Lista de todos os usuários                  |
| **Ver Usuário por ID** | `GET`       | `/api/admin/users/{id}` | **ADMIN** | (Nenhum)                                                                         | `200 OK` - Dados do usuário específico                  |
| **Editar Usuário por ID** | `PUT`       | `/api/admin/users/{id}` | **ADMIN** | `{"name":"...","email":"...","role":"..."}`                                       | `200 OK` - Dados do usuário atualizado                 |
| **Deletar Usuário por ID** | `DELETE`    | `/api/admin/users/{id}` | **ADMIN** | (Nenhum)                                                                         | `204 No Content` (sem corpo na resposta)             |

<br>

---

## 👨‍💻 Autor

Projeto desenvolvido por **Murilo Toniol Besson**.

[![LinkedIn](https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/murilobesson/)
[![GitHub](https://img.shields.io/badge/GitHub-181717?style=for-the-badge&logo=github&logoColor=white)](https://github.com/murilotoniol/)
