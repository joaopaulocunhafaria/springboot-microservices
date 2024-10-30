package com.microservice.bookservice.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.License;

@OpenAPIDefinition(info = @Info(title = "book Service API", version = "0.01", description = "Documentation of book service API"))
public class OpenApiConfiguration {

    public OpenAPI customOpenAPI() {
        return new OpenAPI().components(new Components()).info(new io.swagger.v3.oas.models.info.Info()
                .title("Book Service API")
                .version("V1")
                .license(
                        new License().name("Apache 2.0").url("http://springdoc.org"))); 
    }

}
