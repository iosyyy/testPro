package com.test.service;

import com.test.entry.UserInfo;
import org.springframework.stereotype.Service;

/**
 * @author 靖鸿宣
 * @since 2021/9/28
 */
@Service
public interface UserinfoServiceImp {
  UserInfo getUserInfoByUserId(Long user_id);
}
