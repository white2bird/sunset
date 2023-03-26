package com.sunset.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * SpringDoc API文档相关配置
 * Created by macro on 2022/3/4.
 */
@Configuration
public class SpringDocConfig {
    @Bean
    public OpenAPI mallTinyOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Sunset API")
                        .version("v1.0.0"))
                .externalDocs(new ExternalDocumentation()
                        .description("Sunset 体脂秤接口文档，仅供学习")
                        .url("https://gitee.com/dlongs49/sunset-server.git"));
    }

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("sign")
                .pathsToMatch("/sign/**")
                .build();
    }
}