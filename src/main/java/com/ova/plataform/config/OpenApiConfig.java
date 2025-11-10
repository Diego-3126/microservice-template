package com.ova.plataform.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("OVA Platform Microservice API")
                        .description("Template base para microservicios de la plataforma OVA")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Equipo OVA")
                                .email("equipo@ovaplatform.com")))
                .servers(List.of(
                        new Server()
                                .url("http://localhost:8080")
                                .description("Servidor Local"),
                        new Server()
                                .url("http://ec2-ip:8080")
                                .description("Servidor EC2")
                ));
    }
}