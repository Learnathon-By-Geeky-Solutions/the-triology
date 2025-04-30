package com.smart.health.care.management.system.configtest;

import com.smart.health.care.management.system.config.SwaggerConfig;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SwaggerConfigTest {

    private SwaggerConfig swaggerConfig;

    @BeforeEach
    void setUp() {
        swaggerConfig = new SwaggerConfig();
    }

    @Test
    void testMyCustomConfig() {
        OpenAPI openAPI = swaggerConfig.myCustomConfig();

        assertNotNull(openAPI, "OpenAPI object should not be null");

        Info info = openAPI.getInfo();
        assertNotNull(info, "Info object should not be null");
        assertEquals("Smart Health Care Management System API", info.getTitle(), "API title mismatch");
        assertEquals("API documentation for Smart Health Care Management System", info.getDescription(), "API description mismatch");
        assertEquals("1.0.0", info.getVersion(), "API version mismatch");

        List<Server> servers = openAPI.getServers();
        assertNotNull(servers, "Servers list should not be null");
        assertEquals(3, servers.size(), "There should be 3 server entries");

        assertTrue(servers.stream().anyMatch(server -> "http://localhost:8080".equals(server.getUrl())), "Missing localhost:8080 server");
        assertTrue(servers.stream().anyMatch(server -> "http://localhost:8082".equals(server.getUrl())), "Missing localhost:8082 server");
    }
}