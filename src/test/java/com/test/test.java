package com.test;

import com.test.Components.Sender;
import com.test.dao.UserInfoRepository;
import com.test.entry.UserInfo;
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
  @Resource private UserInfoRepository userDao;

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
