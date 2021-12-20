package com.test.components;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.transaction.RabbitTransactionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

/**
 * @author authoa
 * @since 2021/9/23
 */
@Configuration
@Slf4j
public class Tests {
  @Resource RabbitTemplate rabbitTemplate;
  @Resource private EntityManager entityManager;
  @Resource private DataSource dataSource;

  @Bean
  public Pageable getPageable() {
    return PageRequest.of(1, 1);
  }

  @Bean
  public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
    JpaTransactionManager transactionManager = new JpaTransactionManager();
    transactionManager.setEntityManagerFactory(emf);

    return transactionManager;
  }

  @Bean("rabbitTransactionManager")
  public RabbitTransactionManager getRabbitTransactionManager() {
    return new RabbitTransactionManager(rabbitTemplate.getConnectionFactory());
  }
}
