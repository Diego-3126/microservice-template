# Microservice Template - OVA Platform

![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.0-brightgreen)
![Java](https://img.shields.io/badge/Java-17-blue)
![License](https://img.shields.io/badge/License-MIT-green)
![Architecture](https://img.shields.io/badge/Architecture-Microservices-orange)

Template base para microservicios Spring Boot OVA. Incluye configuración estándar para desarrollo rápido y consistente entre todos los servicios del equipo.

## Características

- ✅ **Spring Boot 3.2** + Java 17
- ✅ **Spring Boot Actuator** - Health checks y métricas
- ✅ **SpringDoc OpenAPI 3** - Documentación automática Swagger UI
- ✅ **Estructura de packages** - Organización estándar para escalabilidad
- ✅ **Manejo global de excepciones** - Respuestas de error consistentes
- ✅ **DTO de respuesta estandarizado** - Formato uniforme para todas las APIs
- ✅ **Configuración YAML** - Configuración organizada y legible
- ✅ **Validación de datos** - Spring Boot Validation integrado
- ✅ **Logging configurado** - Niveles de log apropiados para dev/prod

## Prerequisitos

- **Java 17** o superior
- **Maven 3.6** o superior
- **Spring Boot 3.2.0**

## Inicio Rápido

### 1. Clonar y personalizar el template

```bash
# Clonar el template
git clone https://github.com/Diego-3126/microservice-template.git
cd mi-servicio

# Personalizar el proyecto
# - Editar pom.xml (artifactId, name, description)
# - Editar application.yml (spring.application.name, server.port)
# - Actualizar package names si es necesario
```

### 2. Configuración de aplicación
En pom.xml:

```bash
# <!-- Cambiar estas propiedades -->
<artifactId>mi-nuevo-servicio</artifactId>
<name>mi-nuevo-servicio</name>
<description>Descripción del nuevo servicio</description>
```

En application.yml

```bash
spring:
  application:
    name: mi-nuevo-servicio    # Nombre único para el servicio
    
server:
  port: 8081                   # Puerto único para cada servicio
```

### 3. Ejecutar la aplicación

```bash
# Compilar y ejecutar
mvn clean spring-boot:run

# O compilar y ejecutar el JAR
mvn clean package
java -jar target/mi-nuevo-servicio-1.0.0.jar
```

### Endpoints Disponibles

```bash
Endpoint	         Método	 Descripción	                                     Ejemplo de Uso
/actuator/health    GET     Health check de Spring Boot Actuator              curl http://localhost:8080/actuator/health
/api/health         GET     Health check personalizado del servicio           curl http://localhost:8080/api/health
/api/health/info    GET     Información del servicio y versión                curl http://localhost:8080/api/health/info
/swagger-ui.html    GET     Interfaz Swagger UI - Documentación interactiva   Navegador: http://localhost:8080/swagger-ui.html
/v3/api-docs        GET     Especificación OpenAPI en JSON                    curl http://localhost:8080/v3/api-docs
/api/{recurso}      *       Endpoints específicos del servicio                Personalizar según necesidad
```

### Estructura del Proyecto

```bash
src/main/java/com/ova/platform/
├── Application.java              # Clase principal de Spring Boot
├── config/
│   └── OpenApiConfig.java       # Configuración Swagger/OpenAPI
├── controller/
│   ├── HealthController.java    # Controlador de health checks
│   └── [Nombre]Controller.java  # Controladores de negocio
├── model/
│   ├── dto/
│   │   └── ApiResponse.java     # DTO estándar para respuestas
│   ├── entity/
│   │   └── [Entidad].java       # Entidades JPA
│   └── request/
│       └── [Request].java       # DTOs para requests
├── service/
│   └── [Nombre]Service.java     # Lógica de negocio
├── repository/
│   └── [Nombre]Repository.java  # Repositorios Spring Data JPA
└── exception/
    └── GlobalExceptionHandler.java # Manejador global de excepciones
```

### Configuración
application.yml - configuraciones principales

```bash
server:
  port: 8080                    # Puerto del servicio
  servlet:
    context-path: /             # Context path base

spring:
  application:
    name: microservice-template # Nombre único del servicio
  profiles:
    active: dev                 # Perfil activo (dev, prod, test)

management:
  endpoints:
    web:
      exposure:
        include: health,info,metrics  # Endpoints Actuator expuestos
```

### Perfiles de Configuración
application-dev.yml (Desarrollo):

```bash
logging:
  level:
    com.ova.platform: DEBUG     # Logging detallado en desarrollo

spring:
  datasource:
    url: jdbc:h2:mem:testdb     # BD en memoria para desarrollo
    driver-class-name: org.h2.Driver
    username: sa
    password: ""
```

application-prod.yml (Producción):

```bash
logging:
  level:
    com.ova.platform: INFO      # Logging reducido en producción

spring:
  datasource:
    url: ${DATABASE_URL}        # Variables de entorno en producción
    username: ${DB_USERNAME}
    password: ${DB_PASSWORD}
```

### Desarrollo
Crear un nuevo Controlador

```bash
package com.ova.platform.controller;

import com.ova.platform.model.dto.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ejemplo")
@Tag(name = "Ejemplo", description = "Endpoints de ejemplo para el template")
public class EjemploController {

    @GetMapping
    @Operation(summary = "Obtener datos de ejemplo", description = "Retorna datos de ejemplo con formato estándar")
    public ApiResponse<String> obtenerEjemplo() {
        return ApiResponse.success("Datos de ejemplo", "Ejemplo obtenido exitosamente");
    }

    @PostMapping
    @Operation(summary = "Crear nuevo ejemplo", description = "Crea un nuevo recurso de ejemplo")
    public ApiResponse<String> crearEjemplo(@RequestBody String data) {
        // Lógica de negocio aquí
        return ApiResponse.success(data, "Ejemplo creado exitosamente");
    }
}
```

Usar el ApiResponse estándar

```bash
// Respuesta exitosa con datos
return ApiResponse.success(datos, "Operación exitosa");

// Respuesta exitosa sin datos
return ApiResponse.success(null, "Recurso eliminado exitosamente");

// Respuesta de error
return ApiResponse.error("Mensaje de error específico");

// Respuesta con path específico
ApiResponse<String> response = ApiResponse.success("data", "mensaje");
response.setPath("/api/recurso/123");
return response;
```

Crear una Entidad JPA

```bash
package com.ova.platform.model.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ejemplos")
public class Ejemplo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String nombre;
    
    private String descripcion;
    
    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion = LocalDateTime.now();
    
    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    
    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(LocalDateTime fechaCreacion) { this.fechaCreacion = fechaCreacion; }
}
```

### Despliegue
Despliegue local

```bash
# Compilar y empaquetar
mvn clean package

# Ejecutar
java -jar target/microservice-template-1.0.0.jar

# O con perfil específico
java -jar -Dspring.profiles.active=prod target/microservice-template-1.0.0.jar
```

Despliegue en EC2

```bash
# En la instancia EC2
# 1. Subir el JAR
scp target/microservice-template-1.0.0.jar usuario@ec2-ip:/home/usuario/

# 2. Ejecutar en EC2
java -jar -Dspring.profiles.active=prod microservice-template-1.0.0.jar &

# 3. Verificar
curl http://localhost:8080/actuator/health
```

Docker (Opcional)

```bash
FROM openjdk:17-jdk-slim
VOLUME /tmp
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
```
```bash
# Construir y ejecutar
docker build -t mi-servicio .
docker run -p 8080:8080 mi-servicio
```

Variables de Entorno para Producción

```bash
# application-prod.yml
server:
  port: ${SERVER_PORT:8080}
  
spring:
  datasource:
    url: ${DATABASE_URL}
    username: ${DB_USERNAME}
    password: ${DB_PASSWORD}
    
logging:
  level:
    com.ova.platform: ${LOG_LEVEL:INFO}
```

### Testing
Ejecutar tests

```bash
# Todos los tests
mvn test

# Tests con cobertura (agregar Jacoco al pom.xml)
mvn jacoco:report

# Tests específicos
mvn test -Dtest=NombreClaseTest
```

Ejemplo de tests de controlador

```bash
package com.ova.platform.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
class HealthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void healthCheck_ShouldReturnOk() throws Exception {
        mockMvc.perform(get("/api/health"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("UP"))
                .andExpect(jsonPath("$.service").exists());
    }
}
```

Health Check en Producción

```bash
# Verificar salud del servicio
curl http://localhost:8080/actuator/health

# Verificar información del servicio
curl http://localhost:8080/api/health/info

# Verificar métricas
curl http://localhost:8080/actuator/metrics
```

### Monitoreo
Métricas disponibles

- Health Checks: /actuator/health
- Métricas de aplicación: /actuator/metrics
- Información: /actuator/info
- Condiciones de auto-configuración: /actuator/conditions

Health Check Personalizado
Cada servicio debe implementar sus propios health checks específicos:

```bash
@Component
public class CustomHealthIndicator implements HealthIndicator {
    
    @Autowired
    private MiServicio miServicio;
    
    @Override
    public Health health() {
        try {
            // Verificar conexión a base de datos
            miServicio.verificarConexion();
            
            // Verificar recursos externos
            if (miServicio.estaDisponible()) {
                return Health.up()
                    .withDetail("database", "connected")
                    .withDetail("externalService", "available")
                    .build();
            } else {
                return Health.down()
                    .withDetail("externalService", "unavailable")
                    .build();
            }
        } catch (Exception e) {
            return Health.down(e).build();
        }
    }
}
```

### Contribución
Para nuevos miembros del equipo:

1.  Clonar este template
2.  Personalizar según el microservicio asignado
3.  Seguir la estructura de packages establecida
4.  Mantener consistencia en responses y errores
5.  Documentar nuevos endpoints en Swagger

Convenciones de Código

- Usar ApiResponse para todas las respuestas HTTP
- Seguir la estructura de packages del template
- Documentar endpoints con anotaciones Swagger
- Implementar health checks específicos del servicio
- Usar nombres descriptivos en inglés
- Seguir principios RESTful en endpoints

Ejemplo de Commit Semántico

```bash
feat: add user registration endpoint
fix: resolve database connection timeout
docs: update API documentation
refactor: improve error handling in service layer
test: add unit tests for auth service
```

### Checklist para Nuevos Servicios

- ✅ Actualizar pom.xml (artifactId, name, description)
- ✅ Configurar application.yml (nombre, puerto, perfiles)
- ✅ Implementar entidades específicas del dominio
- ✅ Crear repositorios Spring Data JPA
- ✅ Implementar servicios con lógica de negocio
- ✅ Crear controllers para recursos principales
- ✅ Documentar endpoints con anotaciones Swagger
- ✅ Configurar health checks específicos
- ✅ Implementar tests unitarios y de integración
- ✅ Probar todos los endpoints localmente
- ✅ Verificar despliegue en EC2
- ✅ Configurar variables de entorno en producción
- ✅ Actualizar documentación en repo del equipo

### Solución de problemas
Error: Puerto en uso

```bash
# Cambiar puerto en application.yml
server:
  port: 8081  # Usar puerto diferente

# O encontrar proceso usando el puerto
netstat -tulpn | grep :8080
```

Error: Dependencias no resueltas

```bash
# Limpiar cache de Maven
mvn clean compile

# Forzar descarga de dependencias
mvn dependency:resolve

# Reconstruir todo
rm -rf ~/.m2/repository
mvn clean install
```

Swagger UI no carga

- Verificar que springdoc-openapi-starter-webmvc-ui esté en pom.xml
- Confirmar que la aplicación esté ejecutándose correctamente
- Revisar logs en busca de errores de configuración

Error: No se encuentra Application.java

```bash
# Verificar estructura de packages
src/main/java/com/ova/platform/Application.java

# Recompilar proyecto
mvn clean compile
```

Health Check falla en EC2

```bash
# Verificar security groups en AWS
# Asegurar que puerto 8080 esté abierto

# Verificar desde la instancia
curl http://localhost:8080/actuator/health

# Verificar desde fuera
curl http://<EC2-IP-PUBLICA>:8080/actuator/health
```

¿Problemas o sugerencias? Crear un issue en el repositorio del equipo.

### Licencia
Este proyecto está bajo la Licencia MIT

Construido con ❤️ por el Equipo OVA

```bash

---

## **🎯 ARCHIVOS ADICIONALES RECOMENDADOS**

También puedes crear estos archivos en la raíz del proyecto:

### **1. .gitignore (específico para Spring Boot)**
```

### Maven
- target/
- pom.xml.tag
- pom.xml.releaseBackup
- pom.xml.versionsBackup
- pom.xml.next

### IDE
- .idea/
- *.iws
- *.iml
- *.ipr
- .vscode/
- *.swp
- *.swo

### Logs
- *.log
- logs/

### OS
- .DS_Store
- Thumbs.db

```bash

### **2. LICENSE (Licencia MIT)**
```text
MIT License

Copyright (c) 2024 OVA Team

Permission is hereby granted...
```
