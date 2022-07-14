package com.algaworks.algalog.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private final ResponseMessage m200 = simpleMessage(200, "Chamada realizada com sucesso");
    private final ResponseMessage m201 = simpleMessage(201, "Recurso Criado");
    private final ResponseMessage m204 = simpleMessage(204, "Atualização OK");
    private final ResponseMessage m401 = simpleMessage(401, "Autorização é requerida");
    private final ResponseMessage m403 = simpleMessage(403, "Não autorizado");
    private final ResponseMessage m404 = simpleMessage(404, "Objeto não encontrado");
    private final ResponseMessage m422 = simpleMessage(422, "Erro de validação");
    private final ResponseMessage m500 = simpleMessage(500, "Erro inesperado");

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.POST, Arrays.asList(m201, m403, m422, m500, m200))
                .globalResponseMessage(RequestMethod.GET, Arrays.asList(m403, m404, m500))
                .globalResponseMessage(RequestMethod.PUT, Arrays.asList(m403, m204, m422, m500))
                .globalResponseMessage(RequestMethod.DELETE, Arrays.asList(m403, m404, m200))
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.algaworks.algalog.api.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("AlgaLog")
                .version("v0.0.1")
                .description("Projeto AlgaLog - AlgaWorks")
                .license("AlgaWorks")
                .licenseUrl("https://www.algaworks.com/")
                .contact(new Contact("Ana Alves", "https://github.com/anabalves", "anabalves.dev@gmail.com"))
                .build();
    }

    private ResponseMessage simpleMessage(int code, String msg) {
        return new ResponseMessageBuilder().code(code).message(msg).build();
    }

}
