package com.test.utils;

/**
 * @author 靖鸿宣
 * @since 2021/12/15
 */
public enum ROLE {
  ROLE_ADMIN("ROLE_ADMIN"),
  ROLE_USER("ROLE_USER");
  private String message;

  ROLE(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }
}
