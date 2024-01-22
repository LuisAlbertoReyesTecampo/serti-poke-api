package com.serti.poke.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;


@Configuration
public class SpringFoxConfig {
	
	
	@Bean
	public OpenAPI apiInfo() {
        return new OpenAPI()
            .info(new Info().title("API")
            .description("Serti examen")
            .license(new License().name("Apache 2.0").url("http://springdoc.org"))
            .version("2.0"));
    }

}
