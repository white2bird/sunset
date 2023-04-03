package com.sunset.config;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

@Configuration
public class SpringDocConfig {
    @Bean
    public OpenAPI mallTinyOpenAPI() {
        OpenAPI  openAPI = new OpenAPI()
                .info(new Info().title("Sunset API")
                        .version("v1.0.0"))
                .externalDocs(new ExternalDocumentation()
                        .description("Sunset 体脂秤接口文档，仅供学习")
                        .url("https://gitee.com/dlongs49/sunset-server.git"));
        openAPI.schemaRequirement(HttpHeaders.AUTHORIZATION, this.securityScheme());
        openAPI.addSecurityItem(new SecurityRequirement().addList(HttpHeaders.AUTHORIZATION));

        return openAPI;
    }

//    @Bean
//    public GroupedOpenApi publicApi() {
//        return GroupedOpenApi.builder()
//                .group("v1")
//                .pathsToMatch("/v1/**")
//                .build();
//    }
    private SecurityScheme securityScheme() {
        SecurityScheme securityScheme = new SecurityScheme();
        securityScheme.setType(SecurityScheme.Type.APIKEY);
        //请求头的name
        securityScheme.setName("ms_token");
        securityScheme.setIn(SecurityScheme.In.HEADER);
        return securityScheme;
    }
}