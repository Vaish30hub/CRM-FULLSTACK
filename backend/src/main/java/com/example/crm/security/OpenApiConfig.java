package com.example.crm.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI crmOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("CRM Software API")
                        .description("CRM System for Internship Month 4")
                        .version("1.0"));
    }
}