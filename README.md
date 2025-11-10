# Microservice Template - OVA Platform

![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.0-brightgreen)
![Java](https://img.shields.io/badge/Java-17-blue)
![License](https://img.shields.io/badge/License-MIT-green)
![Architecture](https://img.shields.io/badge/Architecture-Microservices-orange)

Template base para microservicios Spring Boot de la plataforma OVA. Incluye configuración estándar para desarrollo rápido y consistente entre todos los servicios del equipo.

## 🚀 Características

- ✅ **Spring Boot 3.2** + Java 17
- ✅ **Spring Boot Actuator** - Health checks y métricas
- ✅ **SpringDoc OpenAPI 3** - Documentación automática Swagger UI
- ✅ **Estructura de packages** - Organización estándar para escalabilidad
- ✅ **Manejo global de excepciones** - Respuestas de error consistentes
- ✅ **DTO de respuesta estandarizado** - Formato uniforme para todas las APIs
- ✅ **Configuración YAML** - Configuración organizada y legible
- ✅ **Validación de datos** - Spring Boot Validation integrado
- ✅ **Logging configurado** - Niveles de log apropiados para dev/prod

## 📋 Prerequisitos

- **Java 17** o superior
- **Maven 3.6** o superior
- **Spring Boot 3.2.0**

## 🏃‍♂️ Inicio Rápido

### 1. Clonar y personalizar el template

```bash
# Clonar el template (reemplazar con URL real del repositorio)
git clone https://github.com/equipo-ova/microservice-template.git mi-servicio
cd mi-servicio

# Personalizar el proyecto
# - Editar pom.xml (artifactId, name, description)
# - Editar application.yml (spring.application.name, server.port)
# - Actualizar package names si es necesario
