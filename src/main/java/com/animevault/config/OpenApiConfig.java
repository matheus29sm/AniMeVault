package com.animevault.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("AniMeVault API")
                        .version("0.0.1")
                        .description("API documentation for AniMeVault"))
                .servers(List.of(
                        new Server().url("http://localhost:8080").description("Local environment")
                ));
    }

}