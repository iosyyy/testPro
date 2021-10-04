package com.test;

import com.test.components.Sender;
import com.test.dao.UserInfoRepository;
import com.test.entry.UserInfo;
import com.test.proper.JwtSecurityProperties;
import com.test.proper.TestAsnyc;
import com.test.service.UserServiceImp;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import javax.annotation.Resource;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author authoa
 * @since 2021/9/23
 */
@SpringBootTest
public class test {
  @Resource Sender sender;
  @Resource JavaMailSenderImpl mailSender;
  @Resource JwtSecurityProperties properties;

  @Resource UserServiceImp userServiceImp;
  @Resource TestAsnyc testAsnyc;
  @Resource private UserInfoRepository userDao;

  @Test
  void testMessageWith() {
    String emailServiceCode = "1234";
    SimpleMailMessage message = new SimpleMailMessage();
    message.setSubject("注册验证码");
    message.setText("注册验证码是：" + emailServiceCode);
    message.setTo("259203284@qq.com");
    message.setFrom("626797813@qq.com");
    mailSender.send(message);
  }

  @Test
  @SneakyThrows
  void getTest() {
    long l = System.currentTimeMillis();
    CompletableFuture<String> hello = testAsnyc.getHello();
    long y = System.currentTimeMillis();

    System.out.println(hello.get(2000, TimeUnit.MILLISECONDS));
    long x = System.currentTimeMillis();
    System.out.println(x - y);
  }

  @Test
  public void testUserService() {

    /* UserIn build =
            UserIn.builder()
                .uuid("10001")
                .userName("hellos")
                .email("hellos")
                .nickName("test")
                .passWord("111")
                .regTime("2001-07-17")
                .icon("null")
                .build();
    */
    UserInfo userInfo = UserInfo.builder().id(10L).build();
    userDao.delete(userInfo);
  }

  @Test
  public void getPro() {
    System.out.println(properties);
  }

  @Test
  public void testPush() {
    sender.push();
  }

  @Test
  public void testRoute() {
    sender.route();
  }

  @Test
  public void testTopics() {
    sender.topic();
  }

  @Test
  public void testSender() {
    for (int i = 0; i < 10; i++) {
      sender.send(i);
    }
  }
}
