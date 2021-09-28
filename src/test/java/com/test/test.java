package com.test;

import com.test.Components.Sender;
import com.test.entry.UserIn;
import com.test.proper.JwtSecurityProperties;
import com.test.service.UserServiceImp;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author 靖鸿宣
 * @since 2021/9/23
 */
@SpringBootTest
public class test {
  @Resource Sender sender;

  @Resource JwtSecurityProperties properties;

  @Resource UserServiceImp userServiceImp;

  @Test
  public void testUserService() {
    UserIn build =
        UserIn.builder()
            .uuid("10001")
            .userName("hellos")
            .email("hellos")
            .nickName("test")
            .passWord("111")
            .regTime("2001-07-17")
            .icon("null")
            .build();
    System.out.println(userServiceImp.saveUser(build));
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
