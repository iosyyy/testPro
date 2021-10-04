package com.test.controller;

import com.google.common.base.Preconditions;
import com.test.utils.RETURNCODE;
import com.test.utils.RequestParamWrapper;
import com.test.utils.ReturnResult;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.cache.Cache;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.web.bind.annotation.PostMapping;
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
  public static final String expr =
      "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})$";

  @Resource AmqpTemplate amqpTemplate;
  @Resource CaffeineCacheManager cacheManager;

  @PostMapping("/send_email")
  public ReturnResult sendEmail(@RequestBody RequestParamWrapper requestParamWrapper) {
    String email = requestParamWrapper.getEmail();

    Preconditions.checkArgument(Strings.isNotBlank(email), "email is empty");
    Preconditions.checkArgument(email.matches(expr), "email is not a email");
    String code =
        (String) amqpTemplate.convertSendAndReceive("emailListerners", "info.email", email);
    Cache emailCache = cacheManager.getCache("emailCache");
    Preconditions.checkArgument(!Objects.isNull(emailCache), "email is not a email");
    log.info("email:[{}],code:[{}]", email, code);
    emailCache.put(email, code);
    return ReturnResult.builder()
        .code(RETURNCODE.SUCCESS.getCode())
        .msg(RETURNCODE.SUCCESS.getMessage())
        .build();
  }
}
