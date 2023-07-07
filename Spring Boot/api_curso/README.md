# CURSO DE SPRING 

# API MEDICOS 

## ✔️ Técnicas e tecnologias utilizadas

- Spring Boot
- Docker
- Postgres
- Autorização - Spring Security
- Autenticação - Spring Security

### Comandos para rodar o projeto
- Para subir o container: docker-compose up -d
- Para derrubar o container: docker-compose down

### Paginação e Ordenação
- size = `Quantidade de elementos retornados por pagina. O padrão é 20.`
- page = `Representa a pagina da consulta por padrão começa na O.`
- sort = `Parametro responsável pela ordenação dos dados por padrão é ASC.`

### Exemplo de Size
- `http://localhost:8080/medicos?size=1`

### Exemplo de Page
- `http://localhost:8080/medicos?page=1`

### Exemplo de Sort
- `http://localhost:8080/medicos?sort=nome`

### Exemplo de utilização na pratica
- `http://localhost:8080/medicos?size=10&page=1&sort=nome`

### Para modificar as configurações padrões da paginação utilize @PageableDefault
- `lista(@PageableDefault(size = 10, sort = {"nome"})`

### Para trocar os nomes dos parametros adione os comandos abaixo no arquivo application.properties.
- `spring.data.web.pageable.page-parameter=pagina`
- `spring.data.web.pageable.size-parameter=tamanho`
- `spring.data.web.sort.sort-parameter=ordem`


### DOCUMENTAÇÃO

- `http://localhost:8080/swagger-ui/index.html#/`
- `http://localhost:8080/v3/api-docs`

### Configuração de Deploy

- `Na aba Maven do intellij vá até Lifecycle package Run Maven Build`
- `Foi gerado o arquivo .jar na pasta target`
- `java "-Dspring.profiles.active=prod" "-DDATASOURCE_URL=url_do_banco" "-DDATASOURCE_USERNAME=user_do_banco" "-DDATASOURCE_PASSWORD=password_do_banco" -jar caminho/arquivo.jar`
- `Coomando -Dspring.profiles.active=prod define o profile que será usado`
-  `As variaveis de ambiente -DDATASOURCE foram definidas no profile de prod`