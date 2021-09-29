package com.test.utils;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author 靖鸿宣
 * @since 2021/9/28
 */
@Getter
@Setter
@ToString
public class RequestParamWrapper {
  private String username;
  private String password;
  private String email;
  private String nickName;
  private String icon;
  private Integer code;
}
