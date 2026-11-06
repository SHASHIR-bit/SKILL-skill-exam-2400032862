package com.klef.soa.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Passport Service API - Skill Exam 2")
                        .version("1.0")
                        .description("REST API Documentation for Passport Entity CRUD Operations (PostgreSQL)")
                        .contact(new Contact()
                                .name("D Shashir Prem - 2400032862")
                                .email("2400032862@kluniversity.in")));
    }
}
