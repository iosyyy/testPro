package com.test.entry;

import lombok.Builder;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

/**
 * @author authoa
 * @since 2021/9/28
 */
@Entity(name = "user")
@Builder
public class UserIn {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true)
  private String uuid;

  @Column(nullable = false, unique = true)
  private String userName;

  @Column(nullable = false)
  private String passWord;

  @Column(nullable = false, unique = true)
  private String email;

  @Column(unique = true)
  private String nickName;

  @Column(nullable = false)
  private String regTime;

  @CreationTimestamp
  @Column(nullable = false, updatable = false)
  private Date createdAt;

  @UpdateTimestamp
  @Column(nullable = false)
  private Date updatedAt;

  @Column(nullable = false)
  private String icon;

  public UserIn() {}

  protected UserIn(
      Long id,
      String uuid,
      String userName,
      String passWord,
      String email,
      String nickName,
      String regTime,
      Date createdAt,
      Date updatedAt,
      String icon) {
    this.id = id;
    this.uuid = uuid;
    this.userName = userName;
    this.passWord = passWord;
    this.email = email;
    this.nickName = nickName;
    this.regTime = regTime;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
    this.icon = icon;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUuid() {
    return uuid;
  }

  public void setUuid(String uuid) {
    this.uuid = uuid;
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

  public String getRegTime() {
    return regTime;
  }

  public void setRegTime(String regTime) {
    this.regTime = regTime;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }

  public Date getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(Date updatedAt) {
    this.updatedAt = updatedAt;
  }

  public String getIcon() {
    return icon;
  }

  public void setIcon(String icon) {
    this.icon = icon;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
      return false;
    }
    UserIn userIn = (UserIn) o;
    return Objects.equals(id, userIn.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        id, uuid, userName, passWord, email, nickName, regTime, createdAt, updatedAt, icon);
  }

  @Override
  public String toString() {
    return getClass().getSimpleName()
        + "("
        + "id = "
        + id
        + ", "
        + "uuid = "
        + uuid
        + ", "
        + "userName = "
        + userName
        + ", "
        + "passWord = "
        + passWord
        + ", "
        + "email = "
        + email
        + ", "
        + "nickName = "
        + nickName
        + ", "
        + "regTime = "
        + regTime
        + ", "
        + "createdAt = "
        + createdAt
        + ", "
        + "updatedAt = "
        + updatedAt
        + ")";
  }
}
