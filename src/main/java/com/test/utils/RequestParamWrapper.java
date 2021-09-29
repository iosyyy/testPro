package com.test.utils;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 所有接口通用不在此处做参数检查,使用谷歌给的工具做参数检查等工作,新增变量需要指定具体在哪个接口 如果变量特别多直接新建一个方法类去写就行
 *
 * @author 靖鸿宣
 * @since 2021/9/28
 */
@Getter
@Setter
@ToString
public class RequestParamWrapper {
  /* /api/auth/login 使用@username 和 @password变量 */
  private String username;
  private String password;

  /* /api/auth/register 使用@username,@password,@email,@nickName,@icon,@code变量 */
  private String email;
  private String nickName;
  private String icon;
  private Integer code;
}
