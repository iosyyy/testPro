package com.test.service.serviceHandler;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.test.entry.QUserIn;
import com.test.entry.UserIn;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import java.util.List;

/**
 * @author 靖鸿宣
 * @since 2021/10/22
 */
@Service
@Slf4j
public class TestService {
  @Resource private EntityManager entityManager;

  // 查询工厂实体
  private JPAQueryFactory queryFactory;

  // 实例化控制器完成后执行该方法实例化JPAQueryFactory
  @PostConstruct
  public void initFactory() {
    queryFactory = new JPAQueryFactory(entityManager);
  }

  public List<UserIn> findAll(UserIn user) {
    // 使用 QueryDSL 进行查询
    QUserIn userIn = QUserIn.userIn;
    List<UserIn> user1 =
        queryFactory.select(userIn).from(userIn).where(userIn.userName.eq("string")).fetch();
    log.info("{}", user1);
    return null;
  }
}
