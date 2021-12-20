package com.test.service.serviceHandler;

import com.test.dao.UserInRepository;
import com.test.dao.UserInfoRepository;
import com.test.entry.UserIn;
import com.test.entry.UserInfo;
import com.test.service.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author authoa
 * @since 2021/9/28
 */
@Service
public class UserService implements UserServiceImp {
  @Autowired private UserInRepository userDao;
  @Autowired private UserInfoRepository userInfoDao;

  public List<UserInfo> getUserInfo() {
    return userInfoDao.findAll();
  }

  public boolean isAdmin(long id) {
    Optional<UserIn> optional = userDao.findById(id);
    if (optional.isPresent()) {
      UserIn user1 = optional.get();
      return user1.getAdmin();
    } else {
      return false;
    }
  }

  @Override
  public long getCount() {
    return userDao.count();
  }

  @Override
  public UserIn saveUser(UserIn user) {
    return userDao.save(user);
  }

  @Override
  public UserIn userLoginTest(String auth, String password) {
    UserIn user = userDao.findByUserNameAndPassWord(auth, password);
    if (Objects.isNull(user)) {
      return null;
    }
    Boolean cold = user.getCold();
    if (cold != null && cold) {
      return null;
    }
    return user;
  }

  public boolean register(UserIn user) {
    user.setUuid(UUID.randomUUID().toString());
    user.setRegTime(new Date().toString());
    UserIn save = userDao.save(user);
    return !Objects.isNull(save);
  }
}
