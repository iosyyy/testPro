package com.test.filters;

import com.test.proper.JwtSecurityProperties;
import com.test.utils.JwtTokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author authoa
 * @since 2021/9/28
 */
@Component
@Slf4j
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

  @Resource private JwtTokenUtils jwtTokenUtils;

  @Resource private JwtSecurityProperties jwtSecurityProperties;

  @Override
  protected void doFilterInternal(
      HttpServletRequest httpServletRequest,
      HttpServletResponse httpServletResponse,
      FilterChain filterChain)
      throws ServletException, IOException {
    String requestRri = httpServletRequest.getRequestURI();
    // 获取request token
    String token = null;
    String bearerToken = httpServletRequest.getHeader(jwtSecurityProperties.getHeader());
    if (StringUtils.hasText(bearerToken)
        && bearerToken.startsWith(jwtSecurityProperties.getTokenStartWith())) {
      token = bearerToken.substring(jwtSecurityProperties.getTokenStartWith().length());
    }

    if (StringUtils.hasText(token) && jwtTokenUtils.validateToken(token)) {
      Authentication authentication = jwtTokenUtils.getAuthentication(token);
      SecurityContextHolder.getContext().setAuthentication(authentication);
      log.debug(
          "set Authentication to security context for '{}', uri: {}",
          authentication.getName(),
          requestRri);
    } else {
      log.debug("no valid JWT token found, uri: {}", requestRri);
    }
    filterChain.doFilter(httpServletRequest, httpServletResponse);
  }
}
