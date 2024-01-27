package com.blog.application.apis.configurations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.blog.application.apis.utils.AppConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

    private ApiKey apiKeys() {
        return new ApiKey("JWT", AppConstants.AUTHORIZATION_HEADER, "header");
    }

    private List<SecurityContext> securityContexts() {
        return Arrays
                .asList(SecurityContext
                        .builder()
                        .securityReferences(sf())
                        .build()
                );
    }

    private List<SecurityReference> sf() {

        AuthorizationScope scope = new AuthorizationScope("global", "accessEverything");

        return Arrays.asList(new SecurityReference("JWT", new AuthorizationScope[] { scope }));
    }

    @Bean
    public Docket api() {

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(getInfo())
                .securityContexts(securityContexts())
                .securitySchemes(Arrays.asList(apiKeys()))
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo getInfo() {

        return new ApiInfo(
                "Blogging Application : Backend APIs",
                "This project is developed by Madhurendra Nath Tiwari", "1.0",
                "Terms of Service",
                new Contact(
                        "Madhurendra Nath Tiwari",
                        "https://dev-madhurendra.vercel.app/",
                        "dev.madhurendra@gmail.com"
                ),
                "License of APIS",
                "API license URL",
                Collections.emptyList()
        );
    };

}