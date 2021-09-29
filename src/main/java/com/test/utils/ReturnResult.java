package com.test.utils;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * @author 靖鸿宣
 * @since 2021/9/28
 */
@Getter
@Setter
@Builder
public class ReturnResult {
  private int code;
  private String msg;
  private Map<String, Object> data;

  public ReturnResult(int code, String msg, Map<String, Object> data) {
    this.code = code;
    this.msg = msg;
    this.data = data;
  }

  public ReturnResult(RETURNCODE retcode, Map<String, Object> data) {
    this.code = retcode.getCode();
    this.msg = retcode.getMessage();
    this.data = data;
  }

  @Override
  public String toString() {
    return "ReturnResult{" + "code=" + code + ", msg='" + msg + '\'' + ", data=" + data + '}';
  }
}
