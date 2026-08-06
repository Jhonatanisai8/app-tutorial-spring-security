# App Tutorial Spring Security

Proyecto de ejemplo (tutorial) de autenticación con **Spring Boot + Spring Security + JWT** y base de datos **MySQL**.

## ¿De qué trata el proyecto?

Es una API REST que implementa registro de usuarios, login y generación de tokens JWT.

- **Registro de usuarios** con contraseña encriptada (BCrypt).
- **Login** que devuelve un token JWT de acceso.
- **Refresh token** para renovar el token.
- **Endpoints protegidos** según el rol del usuario (`ADMIN` / `USER`).

### Endpoints

| Método | Ruta         | Acceso     | Descripción                           |
|--------|--------------|------------|---------------------------------------|
| POST   | `/api/registro` | Público  | Registrar un usuario (rol `USER`)     |
| POST   | `/api/login`    | Público  | Iniciar sesión y obtener el JWT       |
| POST   | `/api/refresh-token` | Autenticado | Renovar el token JWT          |
| GET    | `/token`     | Autenticado | Endpoint de prueba (cualquier token válido) |
| GET    | `/usuario`   | Rol `USER` | Endpoint de prueba para usuarios      |
| GET    | `/admin`     | Rol `ADMIN` | Endpoint de prueba para administradores |

## Requisitos

- Java 21
- Maven (o usar el wrapper `mvnw` incluido)
- Docker (para la base de datos MySQL)

## 1. Clonar el proyecto

```bash
git clone https://github.com/Jhonatanisai8/app-tutorial-spring-security.git
cd app-tutorial-spring-security
```

## 2. Levantar la base de datos con Docker

```bash
docker compose up -d
```

Esto levanta MySQL 8 en el puerto **3307** (mapeado al 3306 del contenedor) y crea automáticamente la base de datos `bd_app`.

- Usuario: `root`
- Contraseña: `753159852`
- Base de datos: `bd_app`

## 3. Crear los roles en la base de datos

La aplicación no inserta los roles automáticamente, así que hay que crearlos una sola vez:

```bash
docker exec -it mysql-spring-security mysql -uroot -p753159852 bd_app
```

Dentro de la consola de MySQL ejecuta:

```sql
INSERT INTO roles (nombre) VALUES ('USER');
INSERT INTO roles (nombre) VALUES ('ADMIN');
```

> Nota: el registro de usuarios solo asigna el rol `USER`. Para probar el endpoint `/admin`, crea un usuario con rol `ADMIN` directamente en la base de datos.

## 4. Ejecutar la aplicación

```bash
./mvnw spring-boot:run
```

En Windows:

```bash
mvnw.cmd spring-boot:run
```

La aplicación arranca en `http://localhost:8080`.

### Ejemplo de uso

1. **Registrarse:**

   ```bash
   curl -X POST http://localhost:8080/api/registro \
     -H "Content-Type: application/json" \
     -d '{"userName":"juan","email":"juan@mail.com","password":"123456"}'
   ```

2. **Iniciar sesión:**

   ```bash
   curl -X POST http://localhost:8080/api/login \
     -H "Content-Type: application/json" \
     -d '{"email":"juan@mail.com","password":"123456"}'
   ```

   La respuesta incluye el token de acceso.

3. **Acceder a un endpoint protegido:**

   ```bash
   curl http://localhost:8080/usuario \
     -H "Authorization: Bearer <TU_TOKEN>"
   ```

## Tecnologías

- Java 21
- Spring Boot
- Spring Security
- Spring Data JPA
- JWT (jjwt 0.12.6)
- MySQL 8
- Lombok
