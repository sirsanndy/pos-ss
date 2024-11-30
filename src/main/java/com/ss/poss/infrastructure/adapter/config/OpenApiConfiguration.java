package com.ss.poss.infrastructure.adapter.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {

    @Bean
    public GroupedOpenApi possApi() {
        return GroupedOpenApi.builder()
                .group("poss")
                .pathsToMatch("/**")
                .build();
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("POS SS API")
                        .version("1.0")
                        .description("API for Dummy POS SS")
                        .contact(new Contact()
                                .name("Sirsanndy")
                                .url("http://github.com/sirsanndy")
                                .email("sandy.hasanudin@yahoo.com"))
                        .license(new License()
                                .name("Apache License Version 2.0")));
    }
}
