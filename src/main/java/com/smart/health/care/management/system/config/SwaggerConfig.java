package com.smart.health.care.management.system.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server; // Correct import for Server
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI myCustomConfig() {
        return new OpenAPI()
                .info(new Info()
                        .title("Smart Health Care Management System API")
                        .description("API documentation for Smart Health Care Management System")
                        .version("1.0.0"))
                .servers(Arrays.asList(
                        new Server().url("http://localhost:8081").description("local"),
                        new Server().url("http://localhost:8082").description("live")
                ));
    }
}
