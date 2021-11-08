package br.com.codemasters.bluebank.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket apiDoc(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                	.apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                    .paths(PathSelectors.ant("/**"))
                    .build()
                .apiInfo(metaData());
    }
    private ApiInfo metaData(){
        return new ApiInfoBuilder()
                .title("Documentação da API BlueBank")
                .description("API para gerenciar Transações (Transferência de saldo, saque e depósito) e Cadastro de Clientes, Agencia e Contas")
                .version("1.0")
                .contact(new Contact("Alan Frigério, Marlon Rocha e Viviani Andrade","",""))
                .license("Apache License Version 2.0")
                .licenseUrl("https://www.apache.or.license/LICENSE-2.0")
                .build();
    }
}
