# 🎶 Mixs

## API desenvolvida para construção de um agregador de músicas.

# 🛠 Tecnologias e ferramentas
* **Linguagem:** Java 21
* **Framework Principal:** Spring Boot 3.3.4 (Spring Data JPA, Spring Web, Spring Validation, Spring DevTools com **Basic Auth**)
* **Banco de dados:** PostgreSQL
* **Ferramentas de Suporte:** Docker (para criar um imagem que possa rodar em qualquer máquina), Postman (um cliente para testar as requisições HTTP)
* **API Externa:** Discogs API

# 🏗 Arquitetura
* **Padrão de Arquitetural:** MVC Pattern (Padrão organizacional de resposabilidades), SOLID SRP (Single Responsibility Principle) e DIP (Dependency Inversion Principle).
* **Padrão de Transferência:** DTO Pattern (Padrão para transferência de dados), Mapper Pattern (Padrão utilizado para converter dados para entidades e entidades para dados; obs: feito manualmente)

# ⚙️ Configuração das Variáveis de Ambiente

A API utiliza variáveis de ambiente para proteger dados sensíveis (como credenciais do banco de dados). Antes de rodar, certifique-se de ter um arquivo de configuração com as seguintes chaves:
* `DB_USERNAME`: Usuário do banco de dados (ex: `mixs`)
* `DB_PASSWORD`: Senha do banco de dados (ex: `mixs123`)

Por motivos de segurança, as credenciais reais do banco de dados e chaves do sistema estão configuradas em arquivos locais protegidos pelo `.gitignore`. 

O repositório disponibiliza um arquivo modelo chamado `application.example.yml` e um `docker-compose.example.yml` contendo a estrutura necessária. Para rodar o projeto localmente na sua IDE, siga os passos abaixo:

### 💾 Application
1. Na raiz do pacote de configurações (`src/main/resources`), **duplique** o arquivo `application.example.yml`.
2. Renomeie a cópia para **`application.yml`** (este nome é ignorado pelo Git e lido pelo Spring).
3. Abra o novo `application.yml` e preencha as variáveis com as suas credenciais locais:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5433/parking
    username: ${DB_USERNAME:seu_usuario_aqui}
    password: ${DB_PASSWORD:sua_senha_aqui}
 ```
4. Registre-se no **`Google Cloud`** e registre uma **`API`** e configure as **`credências`**. Copie-as e cole em **`client_id`** e **`client_secret.`**

```yaml
  security:
    oauth2:
      client:
        registration:
          google:
            client_id: sua_credencial
            client_secret: sua_credencial
```

5. Registre-se no **`Discogs`**, vá em **`Configurações`**, depois em **`Desenvolvedores`**, gere seu **`Token`** de acesso e cole em **`token.`**

```yaml
app:
  discogs:
    base-url: https://api.discogs.com
    token: seu_token
```

### 🐋 Docker-Compose
1. Na raiz do pacote de configurações, **duplique** o arquivo `docker-compose.example.yml`.
2. Renomeie a cópia para **`docker-compose.yml`** (este nome é ignorado pelo Git e lido pelo Spring).

* **Observação:** Abra o `docker-compose.yml` e substituia a porta para `5432:5432`, caso não tenha o PostgreSQL instalado localmente, caso contrário, permaneça o arquivo como está:

```yaml
  parking-system:
    image: postgres:16
    volumes:
      - ./Postgres:/var/lib/postgresql/data
    environment:
      POSTGRES_DB: parking
      POSTGRES_USER: ${DB_USERNAME}
      POSTGRES_PASSWORD: ${DB_PASSWORD}
    ports:
      - "5432:5432"
    healthcheck:
      test: ["CMD-SHELL", "pg_isready -U ${DB_USERNAME}"]
      interval: 10s
      timeout: 5s
      retries: 5
```

# 🔐 Acesso de Login
O Spring Security Basic Auth está configurado por padrão com um usuário para acesso:

* `Login: manager`
* `Password: manager123`

Caso tenha interesse de trocar as configurações do usuário padrão basta ir até (`src/main/config/DatabaseSeeder.java`) e mudar os seguintes campos:
* `userAuth.setLogin("o login que deseja.")`
*  `userAuth.setPassword("a senha que deseja.")`
*  `userAuth.setRoles("a role que deseja.")`

```java
@Configuration
public class DatabaseSeeder {

    @Bean
    public CommandLineRunner commandLineRunner(UserAuthRepository repository, PasswordEncoder passwordEncoder){
        return args ->{
            if(repository.count() == 0){
                var userAuthAdmin = new UserAuth();
                userAuthAdmin.setLogin("TROQUE-ME");
                userAuthAdmin.setPassword(passwordEncoder.encode("TROQUE-ME"));
                userAuthAdmin.setRoles(List.of("TROQUE-ME"));

                repository.save(userAuthAdmin);
            }
        };
    }
}
```

# 🚀 Como Executar

## 🐋 Docker Compose
1. Certifique-se de que o seu arquivo `cred.env` (ou `.env`) está na raiz do projeto com as variáveis preenchidas.
2. Suba os containers com o comando:
   ```bash
   docker compose up --build

3. Para interromper a execução, no terminal do projeto, use o comando:
   ```bash
   docker compose down
