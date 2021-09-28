package com.test.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author 靖鸿宣
 * @since 2021/9/25
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

  @Bean
  public Docket webApiConfig() {

    return new Docket(DocumentationType.SWAGGER_2)
        .groupName("webApi")
        .apiInfo(webApiInfo())
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.test.controller"))
        .paths(PathSelectors.any())
        .build();
  }

  private ApiInfo webApiInfo() {

    return new ApiInfoBuilder()
        .title("还没想好要写啥的网站的api")
        .description("test001")
        .version("1.0")
        .contact(new Contact("Authoa", "http://localhost.com", "test@qq.com"))
        .build();
  }
}
