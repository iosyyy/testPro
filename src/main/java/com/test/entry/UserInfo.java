package com.test.entry;

import com.test.utils.SEX;
import com.test.utils.USE_STATE;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

/**
 * @author authoa
 * @since 2021/9/29
 */
@Entity(name = "user_info")
@Getter
@Setter
@Builder
public class UserInfo {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(nullable = false)
  private Long id;

  @OneToOne(orphanRemoval = true)
  @JoinColumn(name = "user_id", unique = true)
  private UserIn userIn;

  @CreationTimestamp
  @Column(nullable = false, updatable = false)
  private Date createdAt;

  @UpdateTimestamp
  @Column(nullable = false)
  private Date updatedAt;

  @Column(name = "address")
  private String address;

  @Enumerated
  @Column(name = "state")
  private USE_STATE state;

  @Enumerated
  @Column(name = "sex")
  private SEX sex;

  @Column(name = "age")
  private Integer age;

  @Column(name = "is_admin")
  private Boolean isAdmin;

  public UserInfo(
      Long id,
      UserIn userIn,
      Date createdAt,
      Date updatedAt,
      String address,
      USE_STATE state,
      SEX sex,
      Integer age,
      Boolean isAdmin) {
    this.id = id;
    this.userIn = userIn;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
    this.address = address;
    this.state = state;
    this.sex = sex;
    this.age = age;
    this.isAdmin = isAdmin;
  }

  public UserInfo() {}

  @Override
  public String toString() {
    return "UserInfo{"
        + "id="
        + id
        + ", userIn="
        + userIn
        + ", createdAt="
        + createdAt
        + ", updatedAt="
        + updatedAt
        + ", address='"
        + address
        + '\''
        + ", state="
        + state
        + ", sex="
        + sex
        + ", age="
        + age
        + ", isAdmin="
        + isAdmin
        + '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserInfo userInfo = (UserInfo) o;
    return Objects.equals(id, userInfo.id)
        && Objects.equals(userIn, userInfo.userIn)
        && Objects.equals(createdAt, userInfo.createdAt)
        && Objects.equals(updatedAt, userInfo.updatedAt)
        && Objects.equals(address, userInfo.address)
        && state == userInfo.state
        && sex == userInfo.sex
        && Objects.equals(age, userInfo.age)
        && Objects.equals(isAdmin, userInfo.isAdmin);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, userIn, createdAt, updatedAt, address, state, sex, age, isAdmin);
  }

  // TODO ??????????????????????????????
}
