# Aplicação Spring Boot com MongoDB, RabbitMQ e Redis & Order Management API

Este projeto consiste em uma aplicação Spring Boot robusta que integra MongoDB para persistência de dados, RabbitMQ para comunicação assíncrona via mensagens, e Redis para otimização através de caching e outras funcionalidades. Além disso, expõe uma API RESTful para gerenciamento de pedidos (`Order`), permitindo a criação, recuperação e listagem de pedidos, associando-os a consumidores (`Consumer`).

## Funcionalidades Principais

* Persistência de dados em MongoDB.
* Comunicação assíncrona via RabbitMQ.
* Otimização de desempenho com Redis.
* API REST para gerenciamento de pedidos:
    * Criação de pedidos.
    * Recuperação de pedidos por ID.
    * Listagem de todos os pedidos.

## Pré-requisitos

* Docker e Docker Compose (versão mínima recomendada: 1.29.0)
* Java 17 (ou versão compatível)
* Maven (versão mínima recomendada: 3.8.0) ou Gradle (versão mínima recomendada: 7.0)
* Variáveis de ambiente `RABBITMQ_DEFAULT_USER` e `RABBITMQ_DEFAULT_PASS` configuradas

## Configuração

1.  **Variáveis de Ambiente:**

    * Crie um arquivo `.env` na raiz do projeto e defina as variáveis de ambiente para o RabbitMQ:

        ```
        RABBITMQ_DEFAULT_USER=seu_usuario
        RABBITMQ_DEFAULT_PASS=sua_senha
        ```

    * **Importante:** Garanta a segurança dessas credenciais em ambientes de produção.

2.  **Docker Compose:**

    * O arquivo `docker-compose.yml` define os serviços MongoDB, RabbitMQ e Redis.
    * Para iniciar os serviços:

        ```bash
        docker-compose up -d
        ```

    * Para parar os serviços:

        ```bash
        docker-compose down
        ```

3.  **Configuração do Spring Boot:**

    * Verifique os arquivos `application.properties` ou `application.yml` para configurações de conexão com MongoDB, RabbitMQ e Redis.
    * Exemplo de `application.properties`:

        ```properties
        spring.data.mongodb.uri=mongodb://localhost:27017/sua_base_de_dados
        spring.rabbitmq.host=localhost
        spring.rabbitmq.port=5672
        spring.rabbitmq.username=${RABBITMQ_DEFAULT_USER}
        spring.rabbitmq.password=${RABBITMQ_DEFAULT_PASS}
        spring.redis.host=localhost
        spring.redis.port=6379
        ```

    * Substitua `sua_base_de_dados` pelo nome do seu banco de dados MongoDB.

## Executando a Aplicação

1.  **Build:**

    * Maven: `./mvnw clean install`
    * Gradle: `./gradlew clean build`

2.  **Execução:**

    * Execute a aplicação Spring Boot a partir do seu IDE ou usando o arquivo JAR:

        ```bash
        java -jar target/sua-aplicacao-0.0.1-SNAPSHOT.jar
        ```

    * Substitua `sua-aplicacao-0.0.1-SNAPSHOT.jar` pelo nome do seu arquivo JAR.

## Acessando os Serviços

* **MongoDB:** `mongodb://localhost:27017`
* **RabbitMQ Management UI:** `http://localhost:15672` (credenciais no arquivo `.env`)
* **Redis:** `localhost:6379`

## Endpoints da API

### Criar Pedido (POST /orders)

* **Requisição:**

    ```json
    {
      "name": "Nome do Consumidor",
      "email": "[email address removed]",
      "address": "Endereço do Consumidor"
    }
    ```

* **Resposta:**

    ```json
    {
      "id": "ID do Pedido",
      "consumer": {
        "name": "Nome do Consumidor",
        "email": "[email address removed]",
        "address": "Endereço do Consumidor"
      },
      "orderDate": "Data do Pedido"
    }
    ```

### Recuperar Pedido (GET /orders/{id})

* **Requisição:** `GET /orders/123`
* **Resposta:** (Similar à resposta de criação de pedido)

### Listar Pedidos (GET /orders)

* **Requisição:** `GET /orders`
* **Resposta:** (Lista de pedidos em formato JSON)

## Dependências

* Spring Boot Data MongoDB
* Spring Boot AMQP (RabbitMQ)
* Spring Boot Data Redis
* Spring Boot Web
* Outras dependências necessárias para sua aplicação.

## Contribuição

Contribuições são bem-vindas! Sinta-se à vontade para abrir issues e pull requests.

## Licença

[Insira a licença aqui, se aplicável]

## Informações de Contato

[Seu nome] - [Seu email] - [Link para seu perfil no GitHub]