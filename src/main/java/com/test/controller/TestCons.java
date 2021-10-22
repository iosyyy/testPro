package com.test.controller;

import com.test.annotation.CacheRemove;
import com.test.utils.RETURNCODE;
import com.test.utils.ReturnResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author authoa
 * @since 2021/9/23
 */
@RestController
@Slf4j
public class TestCons {
  @Resource CacheManager cacheManager;

  @GetMapping("/listAll")
  @PreAuthorize("hasAnyRole('ADMIN')")
  @Cacheable(value = "list", key = "#p0+'.'+ #p1", sync = true, cacheManager = "cacheManager")
  public List<Integer> getAll(Long length, Long id) {
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
  public Boolean getAlls(Long length) {
    System.out.println(cacheManager.getClass().getSimpleName());
    Collection<String> cacheNames = cacheManager.getCacheNames();
    for (String cacheName : cacheNames) {
      log.info(cacheName);
    }
    Cache list1 = cacheManager.getCache("list");
    //    list1.evict(length);
    //    list1.evictIfPresent(length);
    if (!Objects.isNull(list1)) {
      return list1.evictIfPresent(length);
    }
    return false;
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
                put("user", authentication.getAuthorities());
              }
            })
        .build();
  }

  /*  @GetMapping("/deleteAll")
  @CacheEvict(value = "list", key = "#p0+'.'+ #p1")
  public void deleteAll(Long length, Long id) {
    System.out.println("every thing has been delete");
  }*/

  @GetMapping("/deleteAll")
  @CacheRemove(
      value = "list",
      key = {})
  public void deleteAll() {
    System.out.println("every thing has been delete");
  }
}
