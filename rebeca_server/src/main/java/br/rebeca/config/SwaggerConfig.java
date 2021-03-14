package br.rebeca.config;


import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//https://www.treinaweb.com.br/blog/documentando-uma-api-spring-boot-com-o-swagger/
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
          .select()
          .apis(RequestHandlerSelectors.any())
          .apis(RequestHandlerSelectors.basePackage("br.rebeca"))
          .build()
        .useDefaultResponseMessages(false)                                   
        //.globalResponseMessage(RequestMethod.GET, responseMessageForGET())
        //.securitySchemes(Arrays.asList(new ApiKey("Token Access", HttpHeaders.AUTHORIZATION, In.HEADER.name())))
        .apiInfo(apiInfo());
    }


private ApiInfo apiInfo() {
    return new ApiInfoBuilder()
            .title("Rebeca")
            .description("\"Gerador de API para consumo de dados.\"")
            .version("1.0.0")
            .contact(new Contact("Michel", "https://github.com/michelmourasilva", "michelmourasilva@gmail.com"))
            .build();
}

private List<ResponseMessage> responseMessageForGET()
{
    return new ArrayList<ResponseMessage>() {{
        add(new ResponseMessageBuilder()
            .code(500)
            .message("500 message")
            .responseModel(new ModelRef("Error"))
            .build());
        add(new ResponseMessageBuilder()
            .code(403)
            .message("Forbidden!")
            .build());
    }};
}
}
