package com.test.utils;

import lombok.Getter;

/**
 * @author 靖鸿宣
 * @since 2021/9/28
 */
@Getter
public enum RETURNCODE {
  SUCCESS(0, "success"),
  LOGIN_FAIL(10000, "failed to login"),
  REGISTER_FAIL(10001, "failed to register"),
  SERVER_ERROR(5000, "server error");

  private int code;
  private String message;

  RETURNCODE(int code, String message) {
    this.code = code;
    this.message = message;
  }

  @Override
  public String toString() {
    return "RETURNCODE{" + "code=" + code + ", message='" + message + '\'' + '}';
  }
}
