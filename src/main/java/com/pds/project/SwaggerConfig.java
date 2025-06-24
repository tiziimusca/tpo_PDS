package com.pds.project;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

        @Bean
        public OpenAPI customOpenAPI() {
                return new OpenAPI()
                                .info(new Info().title("API de prueba").version("1.0"))
                                .addSecurityItem(new SecurityRequirement().addList("Authorization"))
                                .components(new io.swagger.v3.oas.models.Components()
                                                .addSecuritySchemes("Authorization",
                                                                new SecurityScheme()
                                                                                .name("Authorization")
                                                                                .type(SecurityScheme.Type.APIKEY)
                                                                                .in(SecurityScheme.In.HEADER)
                                                                                .description("Ingrese el token codificado en base64 (por ejemplo QURNSU4=)")));
        }
}
