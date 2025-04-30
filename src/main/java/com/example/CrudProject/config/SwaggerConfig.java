package com.example.CrudProject.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI myCustomConfig(){
        return new OpenAPI().info(
                new Info().title("CRUD Operations Project")
                        .description("By Vedic Raghuvanshi")
        );
            /*.servers(List.of(new Server().url("http://localhost:8080/swagger-ui/index.html").description("local"),
                    new Server().url("http://localhost:8082/swagger-ui/index.html").description("live")))
                .tags(List.of(
                        new Tag().name("User Controller")
                ));*/
    }

}
