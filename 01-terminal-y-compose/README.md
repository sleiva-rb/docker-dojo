# Docker vía Comandos vs. Compose

Necesitamos correr un contenedor basado en una imagen de `PostgreSQL` y además queremos:

- Que el nombre del contendor sea `users-db`
- Montar un volumen que contiene un script para inicializar la base de datos
- Publicar el puerto 5432 del contenedor a través del puerto 5432 del host
- Setear las variables de entorno `POSTGRES_USER` y `POSTGRES_PASSWORD` con los valores `pg-db-user` y `pg-db-password` respectivamente

## Utilizando comandos

Si decidimos utilizar **Docker** a través de comandos, debemos ejecutar en una terminal lo siguiente:

```bash
sudo docker run --name users-db -v $PWD/init.sql:/docker-entrypoint-initdb.d/init.sql -p 5432:5432 -e POSTGRES_USER=pg-db-user -e POSTGRES_PASSWORD=pg-db-password postgres:12.7-alpine
```

## Utilizando Docker Compose

Para realizar lo propuesto usando `docker-compose`, debemos definir un archivo `docker-compose.yml` con lo siguente:

```yaml
version: '3'

services:

  users-db:
    image: postgres:12.7-alpine
    container_name: users-db
    ports:
      - 5432:5432
    volumes:
      - "./init.sql:/docker-entrypoint-initdb.d/init.sql"
    environment:
      POSTGRES_USER: "pg-db-user"
      POSTGRES_PASSWORD: "pg-db-password"
```

Abriendo una terminal en donde se encuentre el archivo, ejecutar lo siguiente:

```bash
sudo docker-compose up --build
```
