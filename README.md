# API de receitas culinárias.

Uma API simples para postar e pesquisar receitas, assim como adicionar comentários.

## Tecnologias Utilizadas

- Spring Boot
- MongoDB
- Java
- Spring Security (JWT, OAuth2)
- Docker
- Swagger (para documentação da API)

## Configuração do Ambiente

Certifique-se de ter o Java e o Maven instalados em sua máquina antes de prosseguir.

## Configuração do Banco de Dados

A aplicação está configurada para utilizar o banco de dados MongoDB. Siga as instruções abaixo para configurar o MongoDB no Docker e integrá-lo à aplicação.

### Configuração do Docker para MongoDB

Certifique-se de ter o Docker instalado em sua máquina. Em seguida, execute o seguinte comando para baixar e iniciar um contêiner MongoDB versão 4.4:

```bash
docker run -d -p 27017:27017 --name mongodb-container mongo:4.4
```

## Como Usar

### Construindo o Projeto

Para construir o projeto, execute o seguinte comando:

```bash
mvn clean install
```

### Executando a Aplicação

Para executar a aplicação, use o comando:

```bash
mvn spring-boot:run
```

## Documentação da API (Swagger)

Você pode acessar a documentação da API utilizando o Swagger. Após iniciar a aplicação, acesse o seguinte URL em seu navegador:
(será necessário efetuar login pelo Postman e obter o token para acessar os endpoints)

http://localhost:8080/swagger-ui.html

