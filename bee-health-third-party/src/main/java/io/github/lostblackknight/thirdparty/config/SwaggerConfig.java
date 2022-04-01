package io.github.lostblackknight.thirdparty.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * Swagger 配置
 *
 * @author chensixiang (chensixiang1234@gmail.com)
 */
@Configuration
@EnableOpenApi
public class SwaggerConfig {

    @Value("${spring.application.name}")
    private String applicationName;

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.OAS_30)
                .select()
                .apis(RequestHandlerSelectors.basePackage("io.github.lostblackknight.auth.controller"))
                .paths(PathSelectors.any())
                .build()
                .groupName(applicationName)
                .apiInfo(apiInfo());
    }


    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("小蜜蜂医疗接口文档")
                .contact(new Contact("陈思祥",
                        "https://lostblackknight.github.io/",
                        "chensixiang1234@gmail.com"))
                .version("1.0.0")
                .description("第三方服务接口文档")
                .build();
    }
}
