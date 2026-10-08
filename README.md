# 🖥️ Hardware Stock API — Gestão de Estoque de Periféricos & Hardware

![Java 21](https://img.shields.io/badge/Java-21-orange?style=for-the-badge&logo=java)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-brightgreen?style=for-the-badge&logo=spring-boot)
![Spring Data JPA](https://img.shields.io/badge/Spring_Data_JPA-Supported-blue?style=for-the-badge)
![H2 Database](https://img.shields.io/badge/Database-H2_In--Memory-darkblue?style=for-the-badge)
![Maven](https://img.shields.io/badge/Build-Maven-red?style=for-the-badge&logo=apache-maven)

API RESTful desenvolvida em **Java 21** e **Spring Boot** voltada para o gerenciamento e controle transacional de estoque de lojas de informática e componentes de hardware (placas de vídeo, processadores, memórias e periféricos).

O projeto adota a **Arquitetura em Camadas (Controller, Service, Repository, Entity)** e aplica conceitos essenciais do mercado de desenvolvimento backend, tais como injeção de dependência, controle de exceções global com códigos HTTP semânticos e validação estrita de regras de negócio.

---

## 🛠️ Tecnologias Utilizadas

- **Linguagem:** Java 21 (LTS)
- **Framework Principal:** Spring Boot 3
- **Persistência de Dados:** Spring Data JPA / Hibernate
- **Banco de Dados:** H2 Database (In-memory para testes)
- **Gerenciador de Dependências:** Apache Maven
- **Ambiente de Desenvolvimento:** Eclipse IDE
- **Testes de Endpoints:** Postman / Insomnia

---

## 🏛️ Arquitetura do Sistema

A aplicação foi estruturada seguindo o padrão de separação de responsabilidades (SOLID - Single Responsibility):

```text
com.joaomelo.estoqueapi
 ├── config        # Configurações iniciais e população automática do banco H2
 ├── controller    # Endpoints REST e manipulação de DTOs/Verbos HTTP
 ├── exception     # Tratamento global de erros (@RestControllerAdvice)
 ├── model         # Entidades mapeadas via JPA (@Entity)
 ├── repository    # Interfaces de acesso ao banco (Derived Queries)
 └── service       # Regras de negócio e controle transacional

 
⚙️ Regras de Negócio Implementadas
Validação de Preço e Saldo: Impedimento do cadastro ou atualização de produtos com preços menores ou iguais a zero ou saldo negativo.

Controle Transacional de Baixa: Validação de disponibilidade de saldo antes de registrar vendas de produtos. Lança exceção explicativa caso o saldo seja insuficiente.

Reposição de Estoque: Endpoint dedicado para dar entrada em lotes de mercadorias.

Tratamento de Exceções Amigável: Interceptação global de falhas operacionais via @RestControllerAdvice, retornando respostas padronizadas em formato JSON com o código HTTP apropriado (400 Bad Request, 404 Not Found, etc.) em vez do erro padrão 500.

Carga Inicial Automatizada: Utilização de CommandLineRunner para popular o banco de dados em memória com produtos de teste no momento em que o servidor é iniciado.

🚀 Como Executar o Projeto Localmente
Pré-requisitos
Java 17 ou superior instalado (Recomendado Java 21)

Git instalado na máquina

IDE de sua preferência (Eclipse, IntelliJ IDEA ou VS Code)

Passo a passo
1 Clone o repositório:

git clone [https://github.com/Joaomelo06/estoque-hardware-api.git](https://github.com/Joaomelo06/estoque-hardware-api.git)

2 Acesse a pasta do projeto:

 cd estoque-hardware-api

3 Execute a aplicação via Maven Wrapper:
  ./mvnw spring-boot:run

4  Acesse a API:
A aplicação estará rodando na porta 8080 (ou 8081 caso haja portas ocupadas):

http://localhost:8080/api/produtos

5 Console do Banco H2:
Para visualizar as tabelas do banco em memória no navegador:

URL: http://localhost:8080/h2-console

JDBC URL: jdbc:h2:mem:estoquedb

User: sa | Password: (em branco)
