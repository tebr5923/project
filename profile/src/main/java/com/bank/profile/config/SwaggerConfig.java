package com.bank.profile.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Класс конфигурации swagger.
 * Все URL к докам стандартные, ссылки в HELP.md
 */
@Configuration
public class SwaggerConfig {

    //Бин настраивает название, версию и автора для swagger.
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(
                new Info()
                        .title("P_SS_BANK_2_Profile")
                        .version("1.0.0")
                        .contact(
                                new Contact().name("Ivan Pereverzev")));
    }

}
