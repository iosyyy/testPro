package com.test.controller;

import com.test.utils.JwtTokenUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

  private final JwtTokenUtils jwtTokenUtils;

  public AuthController(JwtTokenUtils jwtTokenUtils) {
    this.jwtTokenUtils = jwtTokenUtils;
  }

  @ApiOperation("登录授权")
  @GetMapping(value = "/login")
  public String login(String user, String password) {
    Map<String, Object> map = new HashMap<>();
    map.put("user", user);
    map.put("password", password);
    return jwtTokenUtils.createToken(map);
  }
}
