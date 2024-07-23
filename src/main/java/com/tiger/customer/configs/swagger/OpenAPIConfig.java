package com.tiger.customer.configs.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Ecommerce APi")
                        .version("v1.0")
                        .description("Description of the API")
                        .termsOfService("http://swagger.io/terms/")
                        .contact(new Contact().name("Ta Duy Hoang").email("duyhoangptit@gmail.com"))
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")))
//                .addSecurityItem(new SecurityRequirement().addList("bearerAuth")) // add require security for all api
                .components(new io.swagger.v3.oas.models.Components()
                        .addSecuritySchemes("bearerAuth", new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")));
    }

}
