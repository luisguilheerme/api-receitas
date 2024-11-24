# Micro Serviços - API de receitas culinárias.

Uma API simples para postar e pesquisar receitas, assim como adicionar comentários e um micro serviço de notificações via e-mail, utilizando e mensageria assíncrona.

## Tecnologias Utilizadas

- Spring Boot
- MongoDB
- Java
- Spring Security (JWT, OAuth2)
- Docker & Docker Compose
- RabbitMQ
- Swagger (para documentação da API)

## Configuração do Ambiente

Certifique-se de ter o Java, o Maven e o Docker instalados em sua máquina antes de prosseguir.
Na raiz do projeto, execute o seguinte comando para subir os contêineres:

```bash
docker-compose up -d
```

## Como Usar

Para inserir receitas, comentários e testar serviço de e-mails, será necessário seguir alguns passos:
- Acessar o arquivo TestConfig.java, dentro do pacote config e adicionar um novo usuário:

```bash
User seuUsuario = new User(null, "Seu nome", "seuemail@gmail.com", "sua senha");

Role user = new Role(null, "ROLE_USER");	

seuUsuario.addRole(user);

userRepository.save(seuUsuario));
```

- Configurar as variáveis de ambiente:
EMAIL_USERNAME com seu email;
EMAIL_PASSWORD com sua senha de app do gmail.

### Construindo o Projeto e Executando os Serviços

#### API de Receitas

Navegue até a pasta onde o código da API de receitas está localizado:

```bash
cd receitas-service
```

Construa o projeto:

```bash
mvn clean install
```

Execute a aplicação:

```bash
mvn spring-boot:run
```

#### Serviço de E-mails

Navegue até a pasta onde o código do serviço de e-mails está localizado:

```bash
cd email-service
```

Construa o projeto:

```bash
mvn clean install
```

Execute a aplicação:

```bash
mvn spring-boot:run
```

## Documentação da API (Swagger)

Você pode acessar a documentação da API utilizando o Swagger. Após iniciar a aplicação, acesse o seguinte URL em seu navegador:
(será necessário efetuar login pelo Postman e obter o token para acessar os endpoints)

http://localhost:8080/swagger-ui.html

