package com.test.controller;

import com.google.common.base.Preconditions;
import com.test.entry.UserIn;
import com.test.service.serviceHandler.UserService;
import com.test.utils.JwtTokenUtils;
import com.test.utils.RETURNCODE;
import com.test.utils.RequestParamWrapper;
import com.test.utils.ReturnResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 靖鸿宣
 * @since 2021/9/28
 */
@Slf4j
@RestController
@RequestMapping("/api/auth")
@Api(tags = "系统授权接口")
public class AuthController {
  @Resource private JwtTokenUtils jwtTokenUtils;
  @Resource private UserService userService;

  @ApiOperation("登录授权")
  @PostMapping(value = "/login")
  public ReturnResult login(@RequestBody RequestParamWrapper user) {
    String username = user.getUsername();
    String password = user.getPassword();
    Preconditions.checkArgument(!Strings.isEmpty(username), "parameter username is blank");
    Preconditions.checkArgument(!Strings.isEmpty(password), "parameter password is blank");

    password = DigestUtils.sha256Hex(password);
    Map<String, Object> dataMap =
        new HashMap<String, Object>() {
          {
            put("username", username);
          }
        };
    if (userService.userLoginTest(username, password)) {
      Map<String, Object> map = new HashMap<>();
      map.put("date", new Date());
      map.put("username", username);
      String token = jwtTokenUtils.createToken(map);
      dataMap.put("token", token);
      log.info("user {} login success.", username);
      return ReturnResult.builder()
          .code(RETURNCODE.SUCCESS.getCode())
          .msg(RETURNCODE.SUCCESS.getMessage())
          .data(dataMap)
          .build();
    }

    return ReturnResult.builder()
        .code(RETURNCODE.LOGIN_FAIL.getCode())
        .msg(RETURNCODE.LOGIN_FAIL.getMessage())
        .data(dataMap)
        .build();
  }

  @ApiOperation("注册")
  @PostMapping(value = "/register")
  public ReturnResult register(@RequestBody RequestParamWrapper user) {
    log.info("{}", user);
    String username = user.getUsername();
    String password = user.getPassword();
    String icon = user.getIcon();
    String nickName = user.getNickName();
    String email = user.getEmail();
    Integer code = user.getCode();
    Preconditions.checkArgument(!Strings.isEmpty(username), "parameter username is blank");
    Preconditions.checkArgument(!Strings.isEmpty(password), "parameter password is blank");
    Preconditions.checkArgument(!Strings.isEmpty(icon), "parameter icon is blank");
    Preconditions.checkArgument(!Strings.isEmpty(email), "parameter email is blank");
    Preconditions.checkArgument(!Strings.isEmpty(nickName), "parameter nickName is blank");
    Preconditions.checkArgument(code != null, "parameter code is blank"); // TODO 等待后续加入邮箱验证码
    password = DigestUtils.sha256Hex(password);
    UserIn userIn =
        UserIn.builder()
            .userName(username)
            .passWord(password)
            .email(email)
            .nickName(nickName)
            .icon(icon)
            .build();

    Map<String, Object> dataMap =
        new HashMap<String, Object>() {
          {
            put("username", username);
          }
        };
    if (userService.register(userIn)) {
      Map<String, Object> map = new HashMap<>();
      map.put("date", new Date());
      map.put("username", username);
      String token = jwtTokenUtils.createToken(map);
      dataMap.put("token", token);
      log.info("user {} register success.", username);
      return ReturnResult.builder()
          .code(RETURNCODE.SUCCESS.getCode())
          .msg(RETURNCODE.SUCCESS.getMessage())
          .data(dataMap)
          .build();
    }

    return ReturnResult.builder()
        .code(RETURNCODE.REGISTER_FAIL.getCode())
        .msg(RETURNCODE.REGISTER_FAIL.getMessage())
        .data(dataMap)
        .build();
  }
}
