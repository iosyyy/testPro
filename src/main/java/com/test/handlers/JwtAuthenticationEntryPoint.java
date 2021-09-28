package com.test.handlers;

import lombok.SneakyThrows;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 靖鸿宣
 * @since 2021/9/28
 */
@Component

public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
  @Override
  @SneakyThrows
  public void commence(HttpServletRequest request,
                       HttpServletResponse response,
                       AuthenticationException authException){
    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException==null?"Unauthorized":authException.getMessage());
  }
}