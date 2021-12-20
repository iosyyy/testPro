package com.test.entry;

import lombok.Builder;
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
  @Id @GeneratedValue private Long id;

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

  @Column(nullable = false)
  private String descition;

  @Column(name = "is_admin")
  private Boolean isAdmin;

  @Column(name = "is_cold")
  private Boolean isCold;

  @OneToOne(orphanRemoval = true)
  @JoinColumn(name = "cart_id")
  private Cart cart;

  public UserIn(
      Long id,
      String uuid,
      String userName,
      String passWord,
      String email,
      String nickName,
      String regTime,
      Date createdAt,
      Date updatedAt,
      String icon,
      String descition,
      Boolean isAdmin,
      Boolean isCold,
      Cart cart) {
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
    this.descition = descition;
    this.isAdmin = isAdmin;
    this.isCold = isCold;
    this.cart = cart;
  }

  public UserIn(Long id) {
    this.id = id;
  }

  public UserIn() {}

  public String getDescition() {
    return descition;
  }

  public void setDescition(String descition) {
    this.descition = descition;
  }

  public Boolean getCold() {
    return isCold;
  }

  public void setCold(Boolean cold) {
    isCold = cold;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserIn userIn = (UserIn) o;
    return Objects.equals(id, userIn.id)
        && Objects.equals(uuid, userIn.uuid)
        && Objects.equals(userName, userIn.userName)
        && Objects.equals(passWord, userIn.passWord)
        && Objects.equals(email, userIn.email)
        && Objects.equals(nickName, userIn.nickName)
        && Objects.equals(regTime, userIn.regTime)
        && Objects.equals(createdAt, userIn.createdAt)
        && Objects.equals(updatedAt, userIn.updatedAt)
        && Objects.equals(icon, userIn.icon)
        && Objects.equals(isAdmin, userIn.isAdmin)
        && Objects.equals(cart, userIn.cart);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        id, uuid, userName, passWord, email, nickName, regTime, createdAt, updatedAt, icon, isAdmin,
        cart);
  }

  public Cart getCart() {
    return cart;
  }

  public void setCart(Cart cart) {
    this.cart = cart;
  }

  public Boolean getAdmin() {
    return isAdmin;
  }

  public void setAdmin(Boolean admin) {
    isAdmin = admin;
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
}
