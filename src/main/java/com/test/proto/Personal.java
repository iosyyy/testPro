package com.test.proto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Objects;

/**
 * @author 靖鸿宣
 * @since 2021/10/11
 */
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Personal {
  private Long id;
  private String name;
  private String email;
  private Date birthday;
  private User user;

  @Override
  public String toString() {
    return "Personal{"
        + "id="
        + id
        + ", name='"
        + name
        + '\''
        + ", email='"
        + email
        + '\''
        + ", birthday="
        + birthday
        + ", user="
        + user
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
    Personal personal = (Personal) o;
    return Objects.equals(id, personal.id)
        && Objects.equals(name, personal.name)
        && Objects.equals(email, personal.email)
        && Objects.equals(birthday, personal.birthday)
        && Objects.equals(user, personal.user);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, email, birthday, user);
  }
}
