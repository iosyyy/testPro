package com.test.entry;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;
import java.util.Objects;

/**
 * @author 靖鸿宣
 * @since 2021/9/28
 */
@Entity(name = "user")
@Getter
@Setter
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

  public UserIn() {}

  protected UserIn(Long id,
                String uuid,
                String userName,
                String passWord,
                String email,
                String nickName,
                String regTime, Date createdAt, Date updatedAt, String icon) {
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
    return 0;
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
