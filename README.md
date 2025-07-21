# 🌿 NinfaGarden - API RESTful + Frontend Empaquetado

Bienvenido a **NinfaGarden**, tu ecommerce de plantas.  
Este proyecto combina **Spring Boot**, **MySQL** y un **frontend estático** servido desde el mismo `.jar`.


## 📂 Estructura del Proyecto
```yaml
backend/
├── src/main/java/com/ninfagarden/... # Código Java (API REST)
├── src/main/resources/static/ # HTML, CSS, JS del frontend
├── src/main/resources/application.properties
├── src/main/resources/data.sql
├── pom.xml
└── README.md
```

## ⚙️ Requisitos Previos

- **Java 17+**
- **Maven** instalado
- **MySQL** corriendo


## ⚡️ 1) Configura tu base de datos en MySQL desde consola o usando un cliente como MySQL Workbench.

1️⃣ Crea una base de datos llamada `ninfagarden`:
```sql
CREATE DATABASE ninfagarden;
```

```java
spring.datasource.url=jdbc:mysql://localhost:3306/ninfagarden
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

// EJEMPLO

# CONEXIÓN
spring.datasource.url=jdbc:mysql://localhost:3306/ninfagarden?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=4fhmV8F6yxF6i24

spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=update
spring.sql.init.mode=always

# VER SQL EN CONSOLA
spring.jpa.show-sql=true
```

---

## ⚡️ 2) Ejecuta el proyecto
En la raíz del backend:

```bash
mvn clean install

mvn spring-boot:run
```

## 🌐 3) Accede al Fronten

En tu navegador, abre:

```
http://localhost:8080
```

## 🗃️ 4) Puedes consultar las APIS en:

```
localhost:8080/api/productos
localhost:8080/api/pedidos/usuario/{id} --> en defecto usuario 5 

```

## 🗃️ 5) Datos de prueba

Puedes cargar datos de prueba ejecutando el script `data.sql` que se encuentra en `src/main/resources/`. Este script inserta productos, plantas y herramientas con imágenes y descripciones. Actualmenta ya hay datos iniciales cargados y los puedes ver en el data.sql.

## 🛠️ 6) Herramientas y Dependencia

