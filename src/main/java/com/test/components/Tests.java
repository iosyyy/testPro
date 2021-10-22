package com.test.components;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.transaction.RabbitTransactionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @author authoa
 * @since 2021/9/23
 */
@Configuration
@Slf4j
public class Tests {
  @Resource RabbitTemplate rabbitTemplate;

  @Bean("rabbitTransactionManager")
  public RabbitTransactionManager getRabbitTransactionManager() {
    RabbitTransactionManager transactionManager =
        new RabbitTransactionManager(rabbitTemplate.getConnectionFactory());
    return transactionManager;
  }
}
