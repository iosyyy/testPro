package com.test.utils;

import com.test.proper.JwtSecurityProperties;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.security.Key;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author authoa
 * @since 2021/9/28
 */
@Slf4j
@Component
public class JwtTokenUtils implements InitializingBean {

  private static final String AUTHORITIES_KEY = "auth";
  @Resource private JwtSecurityProperties jwtSecurityProperties;
  private Key key;

  @Override
  public void afterPropertiesSet() {

    byte[] keyBytes = Decoders.BASE64.decode(jwtSecurityProperties.getBase64Secret());
    this.key = Keys.hmacShaKeyFor(keyBytes);
  }

  public String createToken(Map<String, Object> claims) {
    return Jwts.builder()
        .claim(AUTHORITIES_KEY, claims)
        .setId(UUID.randomUUID().toString())
        .setIssuedAt(new Date())
        .setExpiration(
            new Date((new Date()).getTime() + jwtSecurityProperties.getTokenValidityInSeconds()))
        .compressWith(CompressionCodecs.DEFLATE)
        .signWith(key, SignatureAlgorithm.HS512)
        .compact();
  }

  public Date getExpirationDateFromToken(String token) {
    Date expiration;
    try {
      final Claims claims = getClaimsFromToken(token);
      expiration = claims.getExpiration();
    } catch (Exception e) {
      expiration = null;
    }
    return expiration;
  }

  public Authentication getAuthentication(String token) {
    Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
    HashMap<String, Object> map = (HashMap) claims.get("auth");

    Collection<? extends GrantedAuthority> authorities =
        Arrays.stream(new String[] {map.get("role").toString(), map.get("username").toString()})
            .map(SimpleGrantedAuthority::new)
            .collect(Collectors.toList());
    User principal = new User(map.get("id").toString(), map.get("date").toString(), authorities);
    return new UsernamePasswordAuthenticationToken(principal, token, authorities);
  }

  public boolean validateToken(String authToken) {
    try {
      log.info("{}", authToken);
      Jwts.parser().setSigningKey(key).parseClaimsJws(authToken);
      return true;
    } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
      log.info("Invalid JWT signature.");
      e.printStackTrace();
    } catch (ExpiredJwtException e) {
      log.info("Expired JWT token.");
      e.printStackTrace();
    } catch (UnsupportedJwtException e) {
      log.info("Unsupported JWT token.");
      e.printStackTrace();
    } catch (IllegalArgumentException e) {
      log.info("JWT token compact of handler are invalid.");
      e.printStackTrace();
    }
    return false;
  }

  private Claims getClaimsFromToken(String token) {
    Claims claims;
    try {
      claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
    } catch (Exception e) {
      claims = null;
    }
    return claims;
  }
}
