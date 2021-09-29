package com.test.service.serviceHandler;

import com.test.dao.UserInRepository;
import com.test.entry.UserIn;
import com.test.service.UserServiceImp;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

/**
 * @author 靖鸿宣
 * @since 2021/9/28
 */
@Service
public class UserService implements UserServiceImp {
  @Resource private UserInRepository userDao;

  @Override
  public long getCount() {
    return userDao.count();
  }

  @Override
  public UserIn saveUser(UserIn user) {
    return userDao.save(user);
  }

  @Override
  public Long userLoginTest(String auth, String password) {
    UserIn user = userDao.findByUserNameAndPassWord(auth, password);
    if (Objects.isNull(user)) {
      return null;
    }
    return user.getId();
  }

  public boolean register(UserIn user) {
    user.setUuid(UUID.randomUUID().toString());
    user.setRegTime(new Date().toString());
    UserIn save = userDao.save(user);
    return !Objects.isNull(save);
  }
}
