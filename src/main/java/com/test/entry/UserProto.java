package com.test.entry;

import java.util.Objects;

/**
 * @author 靖鸿宣
 * @since 2021/12/18
 */
public class UserProto extends UserIn {
  private Long id;

  private String userName;

  private String passWord;

  private String email;

  private String nickName;

  private String icon;
  private Boolean isAdmin;

  public UserProto(
      Long id,
      String userName,
      String passWord,
      String email,
      String nickName,
      String icon,
      Boolean isAdmin) {
    this.id = id;
    this.userName = userName;
    this.passWord = passWord;
    this.email = email;
    this.nickName = nickName;
    this.icon = icon;
    this.isAdmin = isAdmin;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserProto userProto = (UserProto) o;
    return Objects.equals(id, userProto.id)
        && Objects.equals(userName, userProto.userName)
        && Objects.equals(passWord, userProto.passWord)
        && Objects.equals(email, userProto.email)
        && Objects.equals(nickName, userProto.nickName)
        && Objects.equals(icon, userProto.icon)
        && Objects.equals(isAdmin, userProto.isAdmin);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, userName, passWord, email, nickName, icon, isAdmin);
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getPassWord() {
    return passWord;
  }

  public void setPassWord(String passWord) {
    this.passWord = passWord;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getNickName() {
    return nickName;
  }

  public void setNickName(String nickName) {
    this.nickName = nickName;
  }

  public String getIcon() {
    return icon;
  }

  public void setIcon(String icon) {
    this.icon = icon;
  }

  public Boolean getAdmin() {
    return isAdmin;
  }

  public void setAdmin(Boolean admin) {
    isAdmin = admin;
  }
}
