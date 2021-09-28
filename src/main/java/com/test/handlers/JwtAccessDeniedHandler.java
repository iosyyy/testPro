package com.test.handlers;

import lombok.SneakyThrows;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 靖鸿宣
 * @since 2021/9/28
 */
@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {
  @Override
  @SneakyThrows
  public void handle(
      HttpServletRequest request,
      HttpServletResponse response,
      AccessDeniedException accessDeniedException) {
    response.sendError(HttpServletResponse.SC_FORBIDDEN, accessDeniedException.getMessage());
  }
}
