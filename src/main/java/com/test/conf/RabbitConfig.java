package com.test.conf;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author authoa
 * @since 2021/9/23
 */
@Configuration
public class RabbitConfig {
  @Bean
  public Queue helloQueue() {
    return new Queue("hello");
  }
}
