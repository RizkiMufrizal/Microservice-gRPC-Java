package org.rizki.mufrizal.baseline.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class GeneralConfiguration {

    @Autowired
    private Environment environment;

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .addServersItem(new Server().url(environment.getRequiredProperty("springdoc.baseurl")))
                .info(new Info().title("Mapping API")
                        .description("Mapping sample API")
                        .version("1.0.0")
                        .license(new License().name("Apache 2.0")));
    }

}