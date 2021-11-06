# Ejemplo práctico

Utilizaremos de ejemplo una [API Rest de usuarios](./users) hecha con Kotlin + Spring Boot.

La API expone los siguientes endpoints:

|Ruta|Método|Descripción|
| --- | --- | --- |
|**/api/v1/users**|GET|Lista los usuarios del sistema|
|**/api/v1/users/:id**|GET|Obtiene un usuario a través de su **ID**|

La app necesita leer los datos de una base PostgreSQL para la cual contamos con un [script](./database) de definición y datos de prueba.

Para setear la conexión entre la aplicación y la DB, se deben definir en la app los valores para las siguientes variables de entorno:

- USERS_DATABASE_URL
- USERS_DATABASE_USERNAME
- USERS_DATABASE_PASSWORD

Debemos completar el archivo `docker-compose.yml` con lo necesario para declarar y levantar nuestros servicios.
