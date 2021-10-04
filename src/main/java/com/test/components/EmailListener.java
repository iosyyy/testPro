package com.test.components;

import com.google.common.base.Preconditions;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @author 靖鸿宣
 * @since 2021/10/4
 */
@Component
@Slf4j
public class EmailListener {
  @Value("${spring.mail.username}")
  public String mailUsername;

  @Resource JavaMailSenderImpl mailSender;

  @RabbitListener(
      bindings = {
        @QueueBinding(
            value = @Queue,
            exchange = @Exchange(value = "emailListerners", type = "topic"),
            key = {"info.email"})
      })
  @Cacheable(value = "emailCache", sync = true, key = "#p0")
  @SneakyThrows
  public String processTopic1(String email) {

    Preconditions.checkArgument(Strings.isNotBlank(email), "email is empty");
    Preconditions.checkArgument(Strings.isNotBlank(mailUsername), "mailUsername is empty");
    log.info("send pass to email [{}]", email);
    String emailServiceCode = String.valueOf(UUID.randomUUID());
    SimpleMailMessage message = new SimpleMailMessage();
    message.setSubject("注册验证码");
    message.setText("您的注册验证码是：" + emailServiceCode);
    message.setTo(email);
    message.setFrom(mailUsername);
    mailSender.send(message);
    return emailServiceCode;
  }
}
