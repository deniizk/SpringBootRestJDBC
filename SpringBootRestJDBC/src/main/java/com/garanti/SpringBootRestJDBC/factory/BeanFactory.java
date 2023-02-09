package com.garanti.SpringBootRestJDBC.factory;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class BeanFactory
{
    @Bean
    public OpenAPI springShopOpenAPI()
    {
        return new OpenAPI()
                .info(new Info().title("Öğrenci bilgi sistemi")
                        .description("JAva spring boot rest jdbc projesi")
                        .version("v1.0")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("External doc")
                        .url("https://springshop.wiki.github.org/docs"));
    }
}