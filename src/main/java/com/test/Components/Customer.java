package com.test.Components;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author 靖鸿宣
 * @since 2021/9/24
 */
@Component
public class Customer {

  @RabbitListener(
      bindings = {
        @QueueBinding(value = @Queue, exchange = @Exchange(value = "logs", type = "fanout"))
      })
  public void process(String hello) {
    System.out.println("message1: " + hello);
  }

  @RabbitListener(
      bindings = {
        @QueueBinding(value = @Queue, exchange = @Exchange(value = "logs", type = "fanout"))
      })
  public void process2(String hello) {
    System.out.println("message2: " + hello);
  }

  @RabbitListener(
      bindings = {
        @QueueBinding(
            value = @Queue,
            exchange = @Exchange(value = "dirs"),
            key = {"info"})
      })
  public void processRoute(String hello) {
    System.out.println("message1: " + hello);
  }

  @RabbitListener(
      bindings = {
        @QueueBinding(
            value = @Queue,
            exchange = @Exchange(value = "dirs"),
            key = {"error"})
      })
  public void processRoute2(String hello) {
    System.out.println("message2: " + hello);
  }

  @RabbitListener(
      bindings = {
        @QueueBinding(
            value = @Queue,
            exchange = @Exchange(value = "topics",type="topic"),
            key = {"info.test","info.*"})
      })
  public void processTopic1(String hello) {
    System.out.println("message1: " + hello);
  }

  @RabbitListener(
      bindings = {
        @QueueBinding(
            value = @Queue,
            exchange = @Exchange(value = "topics",type="topic"),
            key = {"info.#"})
      })
  public void processTopic2(String hello) {
    System.out.println("message2: " + hello);
  }


}
