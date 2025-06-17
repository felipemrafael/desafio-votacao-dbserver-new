### APP Votacao Pauta 

## Frameworks utilizados
- #### docker-compose
- #### Java17
- #### Spring-boot
- #### JUnit
- #### Postgresql
- #### Kafka

## Pré requisitos
- #### docker-compose

## Iniciando a aplicação


### Build da imagem docker e start na aplicação e Start da aplicação
```
docker-compose up --build -d
```

# Swagger
http://localhost:8080/v1/api/swagger-ui/index.html


# Versionamento:
### Atenção no versionamento da API, todas as chamadas estão com o prefixo `/v1/` para indicar a versão da API.
No application.yaml, o versionamento da API é configurado como: v1

### Cadastrar Pauta

```
POST http://localhost:8080/v1/api/pauta

{
  "titulo": "titulo da pauta"
}
``` 

### Abrir Sessão

```
POST http://localhost:8080/v1/api/pauta/abrir
{
    "id_pauta": 1,
    "minutos": 4
}
```
### Votar em uma pauta
```
POST http://localhost:8080/v1/api/votos

{
    "id_pauta": 1,
    "id_cooperado": 1,
    "cpf": "26484951004",
    "voto": "Sim"
}
``` 

### Consultar resultado da pauta
```
GET http://localhost:8080/v1/api/resultados/{id}
```

#### Tarefa Bônus 2 - Mensageria e filas
    Foi utilizado o Kafka para mensageria:  

#### Tarefa Bônus 4 - Versionamento da API
    Há varias formas de fazer o versionamento, para este teste foi escolhido pelo path, onde as apis foram construidas com o a versao 1 'v1' .
    Também pode ser feita via URL ex.: "api/v2/", onde o 'v2' pode ser parametrizado nos properties  .



