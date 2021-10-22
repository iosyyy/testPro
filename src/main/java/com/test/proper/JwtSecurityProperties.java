package com.test.proper;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtSecurityProperties {
  /** Request Headers ： Authorization */
  private String header;
  /** 令牌前缀，最后留个空格 Bearer */
  private String tokenStartWith;
  /** Base64对该令牌进行编码 */
  private String base64Secret;
  /** 令牌过期时间 此处单位/毫秒 */
  private Long tokenValidityInSeconds;

  public JwtSecurityProperties() {}

  public JwtSecurityProperties(
      String header, String tokenStartWith, String base64Secret, Long tokenValidityInSeconds) {
    this.header = header;
    this.tokenStartWith = tokenStartWith;
    this.base64Secret = base64Secret;
    this.tokenValidityInSeconds = tokenValidityInSeconds;
  }

  public String getHeader() {
    return header;
  }

  public void setHeader(String header) {
    this.header = header;
  }

  public String getBase64Secret() {
    return base64Secret;
  }

  public void setBase64Secret(String base64Secret) {
    this.base64Secret = base64Secret;
  }

  public Long getTokenValidityInSeconds() {
    return tokenValidityInSeconds;
  }

  public void setTokenValidityInSeconds(Long tokenValidityInSeconds) {
    this.tokenValidityInSeconds = tokenValidityInSeconds;
  }

  /** 返回令牌前缀 */
  public String getTokenStartWith() {
    return tokenStartWith + " ";
  }

  public void setTokenStartWith(String tokenStartWith) {
    this.tokenStartWith = tokenStartWith;
  }
}
