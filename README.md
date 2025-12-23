
# Loupfit Backend Plataforma
Plataforma backend baseada em microserviços para um e-commerce de moda fitness feminina (Loupfit), desenvolvida com foco em arquitetura escalável, boas práticas e containerização.

O projeto simula um ambiente real de produção, com serviços independentes, bancos de dados dedicados e um BFF (Backend For Frontend) centralizando as comunicações.


## 🧠 Visão Geral da Arquitetura
A aplicação é composta por múltiplos microserviços, cada um com sua responsabilidade bem definida:

### 1. user-service (microserviço)
- Responsável pelo controle de usuários, autenticação e autorização, incluindo cadastro, login e gerenciamento de permissões de acesso.


- Banco de dados: PostgreSQL

### 2. product-service (microserviço)
- Responsável pelo cadastro, consulta e manutenção de produtos, incluindo informações comerciais e estruturais.


- Banco de dados: PostgreSQL
Armazenamento de arquivos: MinIO

### 3. asset-service (microserviço)
- Responsável pelo controle e catalogação de ativos físicos da loja, como espelhos, ferro de passar, refletores, pistolas de etiqueta, cabides e outros equipamentos utilizados na operação diária.


- Banco de dados: MongoDB

### 4. supplier-service (microserviço)
- Responsável pelo cadastro e organização de fornecedores, permitindo o controle das informações de origem dos produtos.


- Banco de dados: MongoDB

### 5. order-service (microserviço)
- Responsável pelo processamento e acompanhamento de pedidos, incluindo criação, consulta e histórico.


- Banco de dados: MongoDB

### 6. bff-service (microserviço)
- Atua como Gateway / Backend For Frontend, centralizando a comunicação com o frontend.

## 🛠️ Tecnologias Utilizadas
- Java 17
- Spring Boot 3
- Spring Security com JWT
- Integração com Feign Client
- PostgreSQL
- MongoDB
- MinIO (Object Storage)
- Docker
- Docker Compose
- Maven
- GitHub
- YAML

## 🐳 Arquitetura com Docker

> Todo o ambiente é orquestrado via Docker Compose, permitindo subir a aplicação completa com um único comando.

Características principais:

- Cada microserviço roda em um container isolado
- Bancos de dados independentes por serviço
- Comunicação interna via network Docker
- Portas externas configuráveis por variáveis de ambiente

## ⚙️ Configuração de Variáveis de Ambiente

O projeto utiliza variáveis de ambiente para configuração de bancos, serviços e portas.

Existe no repositório um arquivo de exemplo:

`
.env.example
`

Antes de executar o projeto, crie o arquivo `.env` a partir do comando:

`cp .env.example .env`

Em seguida, edite o arquivo `.env` e preencha os valores necessários.

⚠️ O arquivo .env não é versionado por questões de segurança.

## 🚀 Como Executar o Projeto
### Pré-requisitos

1- Docker

2- Docker Compose

### Passo a passo

1- Clone o projeto 
```bash
git clone https://github.com/Mattheus-Fernandes/loupfit-backend.git  
```

2- Acesse o diretório
```bash
cd loupfit-backend 
```

3- Mude para a seguinte branch
```bash
git checkout main
```

4- Antes de executar o projeto, copie o arquivo e configure as variáveis:
```bash
cp .env.example .env
```

5- Suba o projeto com Docker Compose
```bash
docker-compose up -d
```


Após a execução, todos os serviços estarão disponíveis conforme as portas configuradas no arquivo .env.

## 🔌 Portas dos Microserviços
As portas externas dos serviços podem ser configuradas no arquivo .env.

Exemplo:

`USER_SERVICE_PORT=8080`

`ASSET_SERVICE_PORT=8081`

`CONSUMABLES_SERVICE_PORT=8082`

`SUPPLIER_SERVICE_PORT=8083`

`PRODUCT_SERVICE_PORT=8084`

`ORDER_SERVICE_PORT=8085`

`BFF_SERVICE_PORT=8001`

## 🔐 Segurança
- Autenticação baseada em JWT
- Separação de responsabilidades entre os microserviços
- Proteção de dados sensíveis via variáveis de ambiente

## 📂 Organização do Projeto
```
loupfit-backend/
├── user-service
├── product-service
├── asset-service
├── consumables-service
├── supplier-service
├── order-service
├── bff-service
├── docker-compose.yml
├── .env.example
└── README.md
```
## 🎯 Objetivo do Projeto
Este projeto foi desenvolvido com foco em:

- Prática de arquitetura de microserviços
- Desenvolvimento backend com Java e Spring Boot
- Containerização com Docker
- Organização e padronização de código
- Simulação de um ambiente real de produção

Trata-se de um projeto com necessidade real de uma loja local e eu aproveitei o contexto para estruturar tudo como um projeto de portfólio, aplicando boas práticas e simulando um ambiente de produção.

## 📈 Próximos Passos (Roadmap)

* Configuração de CORS
* Implementação de CI/CD com GitHub Actions
* Deploy em VPS
* Configuração de Nginx como reverse proxy
* HTTPS
* Documentação da API com Swagger

## 👤 Autor

### Mattheus Fernandes

#### Desenvolvedor Backend | Java & Spring Boot

GitHub: https://github.com/Mattheus-Fernandes

## ✅ Observação Final

Este repositório representa a versão backend da plataforma Loupfit, sendo parte de um ecossistema maior que inclui frontend e futuros serviços.