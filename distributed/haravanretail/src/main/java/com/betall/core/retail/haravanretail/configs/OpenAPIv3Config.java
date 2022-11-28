package com.betall.core.retail.haravanretail.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.ArrayList;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
@ComponentScan(basePackages = "com.betall.core.retail.haravanretail.controllers")
public class OpenAPIv3Config {
    @Bean
    public OpenAPI customOpenAPI() {
        final List<Server> allServer = new ArrayList<>();
        allServer.add(new Server().url(""));
        return new OpenAPI()
            .servers(allServer)
            .info(new Info().title("TopUp-PinCode APIs")
                .description("Operation for TopUp-PinCode APIs")
                .contact(new Contact()
                    .email("admin@betall.vn")
                    .name("Administrator")
                    .url("")
                )
                .license(new License()
                    .name("Apache 2.0")
                    .url("http://www.apache.org/licenses/LICENSE-2.0.html"))
                    .version("1.0.0")
            );
    }
}