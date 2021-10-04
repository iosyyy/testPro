package com.test.components;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author authoa
 * @since 2021/9/23
 */
@Component
public class Listener {
  @RabbitListener(queuesToDeclare = @Queue(value = "morssss"))
  public void process(String hello) {
    System.out.println("message1: " + hello);
  }

  @RabbitListener(queuesToDeclare = @Queue(value = "morssss"))
  public void process2(String hello) {
    System.out.println("message2: " + hello);
  }
}
