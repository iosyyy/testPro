package com.test.controller;

import com.test.utils.RETURNCODE;
import com.test.utils.ReturnResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 * @author 靖鸿宣
 * @since 2021/9/23
 */
@RestController
@Slf4j
public class TestCons {
  @Resource CacheManager cacheManager;

  @GetMapping("/listAll")
  @Cacheable(value = "list", key = "#length", sync = true)
  public List<Integer> getAll(Long length) {
    List<Integer> list = new ArrayList<>();
    for (int i = 0; i < length; i++) {
      list.add(i);
    }
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return list;
  }

  @GetMapping("/lists")
  public Object getAlls(Long length) {

    Cache list1 = cacheManager.getCache("list");
    list1.clear();
    Collection<String> cacheNames = cacheManager.getCacheNames();
    for (String cacheName : cacheNames) {
      log.info(cacheName);
    }
    list1.evict(length);
    list1.evictIfPresent(length);
    return list1.evictIfPresent(length);
  }

  @GetMapping("/getUser")
  public ReturnResult getUser() {

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    return ReturnResult.builder()
        .code(RETURNCODE.SUCCESS.getCode())
        .msg(RETURNCODE.SUCCESS.getMessage())
        .data(
            new HashMap<>() {
              {
                put("user", authentication.getName());
              }
            })
        .build();
  }

  @GetMapping("/deleteAll")
  @CacheEvict(value = "list", key = "#length")
  public void deleteAll(Long length) {
    System.out.println("every thing has been delete");
  }
}
