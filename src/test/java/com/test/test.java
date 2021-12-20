package com.test;

import com.test.components.Sender;
import com.test.controller.AuthController;
import com.test.dao.UserInfoRepository;
import com.test.entry.UserIn;
import com.test.entry.UserInfo;
import com.test.proper.JwtSecurityProperties;
import com.test.proper.TestAsnyc;
import com.test.proto.PersonConverter;
import com.test.proto.PersonDTO;
import com.test.proto.Personal;
import com.test.proto.User;
import com.test.service.UserServiceImp;
import com.test.utils.SEX;
import com.test.utils.USE_STATE;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.redisson.api.RDeque;
import org.redisson.api.RKeys;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.MethodIntrospector;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author authoa
 * @since 2021/9/23
 */
@SpringBootTest()
public class test {
  @Resource Sender sender;
  @Resource JavaMailSenderImpl mailSender;
  @Resource JwtSecurityProperties properties;
  @Autowired UserInfoRepository userInfoDao;

  @Resource UserServiceImp userServiceImp;
  @Resource TestAsnyc testAsnyc;
  ForkJoinPool forkJoinPool = new ForkJoinPool();
  @Resource AuthController authController;
  @Resource private UserInfoRepository userDao;
  @Resource private RedissonClient redissonClient;
  @Resource private PersonConverter personConverter;

  @Test
  @SneakyThrows
  public void testService() {

    UserInfo build =
        UserInfo.builder()
            .sex(SEX.BOY)
            .id(2L)
            .createdAt(new Date())
            .updatedAt(new Date())
            .userIn(UserIn.builder().id(11L).build())
            .state(USE_STATE.normal)
            .build();
    userInfoDao.save(build);
  }

  @Test
  public void testAsny() {
    Map<Method, PostMapping> methodResourceMap =
        MethodIntrospector.selectMethods(
            authController.getClass(),
            (MethodIntrospector.MetadataLookup<PostMapping>)
                method -> AnnotationUtils.getAnnotation(method, PostMapping.class));
    System.out.println(methodResourceMap);
    methodResourceMap.forEach(
        (method, resource) -> {
          System.out.println(method);
          System.out.println(resource);
        });
  }

  @Test
  public void test() {
    Personal person =
        new Personal(
            1L,
            "zhige",
            "zhige.me@gmail.com",
            new Date(),
            new User(1),
            new Personal.Address(null, 2));
    PersonDTO personDTO = personConverter.domain2dto(person);
    System.out.println(person.getAddress());
    System.out.println(personDTO);
    assertNotNull(personDTO);
    assertEquals(personDTO.getId(), person.getId());
    assertEquals(personDTO.getName(), person.getName());
    assertEquals(personDTO.getBirth(), person.getBirthday());
    Date birth = personDTO.getBirth();
    System.out.println(birth);
    System.out.println(personDTO.getBirthDateFormat());

    List<Personal> people = new ArrayList<>();
    people.add(person);
    List<PersonDTO> personDTOs = personConverter.domain2dto(people);
    assertNotNull(personDTOs);
  }

  @Test
  @SneakyThrows
  void testLock() {
    RKeys keys = redissonClient.getKeys();
    RLock java = redissonClient.getLock("java");
    java.lock(31, TimeUnit.SECONDS);
    System.out.println("hello world");
    RDeque<Object> hello = redissonClient.getDeque("hellos");
    System.in.read();

    System.out.println(keys.count());
  }

  @Test
  @SneakyThrows
  void testLock2() {
    RKeys keys = redissonClient.getKeys();
    RLock java = redissonClient.getLock("java");
    java.lock();
    RDeque<Object> hello = redissonClient.getDeque("hellos");

    System.out.println(keys.count());
  }

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
