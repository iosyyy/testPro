package com.test.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author 靖鸿宣
 * @since 2021/10/1
 */
@Slf4j
@RestController
@RequestMapping("/api/email")
@Api(tags = "邮箱验证接口")
public class EmailController {
  @Resource CacheManager cacheManager;

  @GetMapping("/send_email")
  public String sendEmail(@RequestBody String email) {
    Cache email1 = cacheManager.getCache("email");
    if (!Objects.isNull(email1)) {
      Cache.ValueWrapper emailCache = email1.get(email);
      if (!Objects.isNull(emailCache)) {
        return String.valueOf(emailCache.get());
      }
    }
    return "";
  }
}
