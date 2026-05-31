package com.tradingengine.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI tradingEngineOpenAPI() {

        return new OpenAPI()
                .info(new Info()
                        .title("Trading Engine API")
                        .description("Backend Trading Engine APIs for Stock Exchange")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Trading Engine Team")
                                .email("support@tradingengine.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0")))
                .externalDocs(new ExternalDocumentation()
                        .description("Project Documentation")
                        .url("https://tradingengine.com/docs"));
    }
}