package com.test.service.serviceHandler;

import com.test.dao.UserInRepository;
import com.test.entry.UserIn;
import com.test.service.UserServiceImp;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
}
