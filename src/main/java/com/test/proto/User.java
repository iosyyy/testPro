package com.test.proto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

/**
 * @author 靖鸿宣
 * @since 2021/10/11
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {
  private Integer age;

  @Override
  public String toString() {
    return "User{" + "age=" + age + '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    User user = (User) o;
    return Objects.equals(age, user.age);
  }

  @Override
  public int hashCode() {
    return Objects.hash(age);
  }
}
