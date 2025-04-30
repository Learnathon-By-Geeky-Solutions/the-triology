package com.smart.health.care.management.system.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.Components;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI myCustomConfig() {
        final String securitySchemeName = "bearerAuth";

        return new OpenAPI()
                .info(new Info()
                        .title("Smart Health Care Management System API")
                        .description("API documentation for Smart Health Care Management System")
                        .version("1.0.0"))
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
                .components(new Components().addSecuritySchemes(securitySchemeName,
                        new SecurityScheme()
                                .name(securitySchemeName)
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                ))
                .servers(Arrays.asList(
                        new Server()
                                .url("https://smart-health-care-management-system.onrender.com")
                                .description("production"),
                        new Server()
                                .url("http://localhost:8080")
                                .description("local"),
                        new Server()
                                .url("http://localhost:8082")
                                .description("live")

                ));
    }
}
