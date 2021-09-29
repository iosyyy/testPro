package com.test.Components;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author 靖鸿宣
 * @since 2021/9/23
 */
@Component
public class Sender {
  @Resource AmqpTemplate amqpTemplate;

  public void push() {
    amqpTemplate.convertAndSend("logs", "", "end");
  }

  public void route() {
    amqpTemplate.convertAndSend("dirs", "info", "发送router消息");
  }

  public void topic() {
    amqpTemplate.convertAndSend("topics", "info.test111", "发送topic消息");
  }

  public void send(int i) {
    amqpTemplate.convertAndSend("morssss", "world");
  }
}
