package com.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author 靖鸿宣
 * @since 2021/9/23
 */
@SpringBootApplication
@EnableCaching
@EnableSwagger2
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}
