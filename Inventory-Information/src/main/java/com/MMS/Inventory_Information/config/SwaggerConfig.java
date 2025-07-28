package com.MMS.Inventory_Information.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

    @Configuration
    public class SwaggerConfig {
        @Bean
        public OpenAPI inventoryOpenAPI() {
            return new OpenAPI()
                    .info(new Info()
                            .title("INSA Inventory Microservice API")
                            .description("REST API documentation for Inventory Management")
                            .version("v1.0")
                            .contact(new Contact()
                                    .name("INSA Dev Team")
                                    .email("support@insa.gov.et")));
        }
    }


