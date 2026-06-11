# ⏰ Sistema de Ponto - API Backend

> **status: 🚧 Em Fase de Construção / Desenvolvimento**

Este repositório contém a API REST (Backend) do **Sistema de Ponto** (Time Tracking System). O projeto foi desenvolvido com a arquitetura monólito em mente, utilizando a versão mais recente do ecossistema Spring.

---

## 🛠️ Tecnologias Utilizadas

O projeto é estruturado utilizando as seguintes tecnologias e frameworks:

- **Linguagem:** [Java 21](https://www.oracle.com/java/technologies/downloads/)
- **Framework Principal:** [Spring Boot 3.5.14](https://spring.io/projects/spring-boot)
- **Banco de Dados & Persistência:**
  - Spring Data JPA (Hibernate)
  - [Flyway Migration](https://flywaydb.org/) (Para controle de versão e evolução do esquema do banco de dados)
- **Segurança & Autenticação:**
  - Spring Security
  - `JWT-Package` (Biblioteca interna personalizada para gerenciamento e validação de tokens JWT)
- **Arquitetura & Nuvem:**
  - Spring Cloud (Eureka Discovery Client/Server para registro de serviços)
  - Resilience4j (Circuit Breaker para resiliência e tolerância a falhas)
- **Documentação de API:**
  - [Springdoc OpenAPI UI / Swagger](https://springdoc.org/) (Versão `2.8.16`)
- **Produtividade:**
  - Lombok (Redução de código boilerplate)
  - Maven (Gerenciador de dependências e build)

---

## 📦 Estrutura de Pacotes do Projeto

O código-fonte principal está organizado dentro de `src/main/java/betolara1/Ponto`:

- 📂 **`config/`**: Configurações gerais da aplicação (CORS, Segurança/Spring Security, Swagger/OpenAPI).
- 📂 **`controller/`**: Controladores REST responsáveis por expor os endpoints HTTP.
- 📂 **`dto/`**: Objetos de Transferência de Dados (Requests e Responses personalizados).
- 📂 **`model/`**: Entidades persistentes mapeadas com JPA (representação das tabelas do banco).
- 📂 **`repository/`**: Interfaces Spring Data JPA para operações de CRUD e consultas personalizadas.
- 📂 **`service/`**: Classes de serviço contendo a regra de negócio da aplicação.
- 📂 **`utils/`**: Utilitários gerais do sistema (ex: paginação e helpers).

---

## 🔐 Configuração de Segurança (JWT)

A autenticação é baseada em tokens JWT. As principais propriedades devem ser configuradas no arquivo [application.properties](file:///c:/Users/Ralf/Desktop/Programa%C3%A7%C3%A3o/SistemaPonto/Back/src/main/resources/application.properties):

```properties
# Nome do microsserviço
spring.application.name=Ponto

# Chave secreta de assinatura do JWT (deve ter pelo menos 32 caracteres seguros)
jwt.secret-key=minha_chave_secreta_super_longa_e_segura_32_chars

# Tempo de expiração em milissegundos (Ex: 43200000ms = 12 horas)
jwt.expiration-time=43200000 

# Rotas públicas que não exigem Token de Autenticação
jwt.excluded-paths=/auth/login, /public/**, /swagger-ui/**
```

---

## 🚀 Como Executar o Projeto Localmente

### Pré-requisitos

1. **Java SDK 21** ou superior instalado e configurado nas variáveis de ambiente (`JAVA_HOME`).
2. **Maven 3.x** instalado (ou utilize o wrapper `./mvnw` incluso no repositório).
3. Banco de dados configurado (conforme necessário nas variáveis de ambiente ou arquivo `application.properties`).

### Passo a Passo

1. **Clonar o repositório:**
   ```bash
   git clone https://github.com/betolara1/Sistema-De-Ponto.git
   cd Sistema-De-Ponto/Back
   ```

2. **Compilar e baixar dependências:**
   ```bash
   ./mvnw clean install
   # No Windows (PowerShell/CMD):
   .\mvnw.cmd clean install
   ```

3. **Iniciar a aplicação:**
   ```bash
   ./mvnw spring-boot:run
   # No Windows (PowerShell/CMD):
   .\mvnw.cmd spring-boot:run
   ```

A API estará disponível por padrão na porta definida (normalmente `8080` caso não especificado de outra forma).

---

## 📖 Documentação da API (Swagger UI)

Com o servidor rodando, você pode visualizar e interagir com todos os endpoints disponíveis acessando a interface do Swagger UI:

- **URL:** [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html) *(substitua a porta se alterada)*

---

## 📌 Endpoints Atuais (Recursos Implementados)

Atualmente, o recurso de **Empresas** está implementado com os seguintes endpoints sob a rota base `/empresa`:

### 🏢 Empresas (`/empresa`)

| Método | Endpoint | Descrição | Parâmetros de Query / Path |
| :--- | :--- | :--- | :--- |
| **GET** | `/empresa` | Retorna lista de empresas paginada e ordenada. | `page` (padrão 0), `size` (padrão 10), `sortBy` (padrão `dateCreated`), `direction` (padrão `desc`) |
| **GET** | `/empresa/{id}` | Busca os detalhes de uma empresa específica por ID. | `{id}` (Path variable) |
| **GET** | `/empresa?active={isActive}` | Lista empresas ativas ou inativas com paginação. | `isActive` (Boolean), `page`, `size`, `sortBy`, `direction` |
| **GET** | `/empresa?dateCreated={dateString}` | Lista empresas pela data de criação. | `dateString` (String), `page`, `size`, `sortBy`, `direction` |
| **GET** | `/empresa?dataUpdated={dateString}` | Lista empresas pela data de atualização. | `dateString` (String), `page`, `size`, `sortBy`, `direction` |
| **GET** | `/empresa?nome={nome}` | Busca uma empresa pelo nome exato. | `nome` (String) |
| **GET** | `/empresa?cnpj={cnpj}` | Busca uma empresa pelo CNPJ. | `cnpj` (String) |
| **POST** | `/empresa` | Cadastra uma nova empresa no sistema. | JSON Body (`SaveEmpresaRequest`) |
| **PUT** | `/empresa/{id}` | Atualiza os dados de uma empresa existente. | JSON Body (`UpdateEmpresaRequest`), `{id}` |
| **DELETE**| `/empresa/{id}` | Desativa temporariamente ou remove uma empresa. | `{id}` |

---

## 📋 Próximos Passos (Roadmap de Desenvolvimento)

- [ ] Criar scripts iniciais de migração de banco de dados (`V1__create_tables.sql`) em `src/main/resources/db/migration`.
- [ ] Implementar a entidade de `Colaborador` (Funcionários).
- [ ] Implementar a lógica de registro de ponto (entradas, saídas, intervalos).
- [ ] Configurar conexão dedicada a banco de dados em produção (PostgreSQL/MySQL).
- [ ] Conectar ao Eureka Server de registro de microsserviços.
