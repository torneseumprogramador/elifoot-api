![java10x Logo](https://java10x.dev/wp-content/uploads/2024/12/logo-java.png)

# Elifoot

Elifoot é uma aplicação desenvolvida em Java com Spring Boot que simula a gestão de clubes de futebol, incluindo funcionalidades como cadastro de estádios, jogadores, clubes, autenticação e autorização com OAuth2, e controle de permissões via scopes.

## ✨ Funcionalidades

- CRUD de Clubes, Estádios e Jogadores
- Relacionamento entre Clubes e Estádios (1:1) e entre Clubes e Jogadores (1:N)
- Autenticação e autorização com Spring Security + OAuth2
- Controle de acesso por *scopes* (`club:read`, `player:write`, etc.)
- Conversão de entidades e DTOs com MapStruct
- Validação global com `@ControllerAdvice`
- Migrations com Flyway
- Testes unitários com JUnit

## 🔐 Segurança

O sistema utiliza OAuth2 com *JWT Bearer Token* e validação baseada em *scopes*, incluindo anotação personalizada `@CanReadClub`.

### Exemplo de Scopes

- `club:read`
- `club:write`
- `player:read`
- `player:write`
- `admin:all`

## 🧱 Diagrama do Banco de Dados

```mermaid
erDiagram
    STADIUM ||--|| CLUB : "has"
    CLUB ||--o{ PLAYER : "has"

    USERS ||--o{ USERS_SCOPES : "has"
    SCOPES ||--o{ USERS_SCOPES : "has"

    STADIUM {
        BIGINT id PK
        VARCHAR name
        VARCHAR city
        INTEGER capacity
        VARCHAR url_img
    }

    CLUB {
        BIGINT id PK
        VARCHAR name
        DATE founded
        VARCHAR url_img
        BIGINT stadium_id FK
    }

    PLAYER {
        BIGINT id PK
        VARCHAR name
        VARCHAR position
        INTEGER shirt_number
        VARCHAR url_img
        BIGINT club_id FK
    }

    USERS {
        BIGINT id PK
        VARCHAR name
        VARCHAR email
        VARCHAR password
        BOOLEAN active
    }

    SCOPES {
        BIGINT id PK
        VARCHAR name
    }

    USERS_SCOPES {
        BIGINT user_id PK, FK
        BIGINT scope_id PK, FK
    }
```

## 🚀 Como rodar o projeto

1. Clone o repositório:

```bash
git clone https://github.com/renanlessa/elifoot.git
```

2. Rode a aplicação:

```bash
./mvnw spring-boot:run
```

3. Acesse: `http://localhost:8080`

## 📡 Endpoints da API
### UserController
- `POST /users`: Criar novo usuário
### LoginController
- `POST /login`: Autenticar usuário
### EnumController
- `GET /enums/positions`: Listar posições de jogadores
### StadiumController
- `GET /stadiums`: Listar estádios
- `POST /stadiums`: Criar novo estádio
### ClubController
- `GET /clubs`: Listar clubes
- `POST /clubs`: Criar novo clube
- `GET /clubs/{id}`: Obter clube por id
- `GET /clubs/{id}/players`: Listar jogadores do clube por id
### PlayerController
- `GET /players`: Listar jogadores
- `POST /players`: Criar novo jogador
- `GET /players/{id}`: Obter jogador por id

## ✅ Testes

Por enquanto, a aplicação conta com testes unitários. Testes de integração serão implementados em breve.

## 📁 Estrutura de pacotes

- `controller`: endpoints REST
- `service`: regras de negócio
- `mapper`: conversões com MapStruct
- `config`: configurações gerais
- `exception`: tratamento global de erros
- `domain`: entidades JPA
- `repository`: acesso a dados

---

### License

MIT License

Copyright (c) 2025 Renan Lessa

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
