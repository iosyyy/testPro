package com.test.service;

import com.test.entry.UserIn;
import org.springframework.stereotype.Service;

/**
 * @author 靖鸿宣
 * @since 2021/9/28
 */
@Service
public interface UserServiceImp {
  long getCount();
  UserIn saveUser(UserIn user);
}
