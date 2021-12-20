package com.test.proto;

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
@Setter
@Getter
public class Personal {
  private Long id;
  private String name;
  private String email;
  private Date birthday;
  private User user;
  private Address address;

  public Personal(Long id, String name, String email, Date birthday, User user, Address address) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.birthday = birthday;
    this.user = user;
    this.address = address;
  }

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

  public static class Address {
    public Integer id;
    public int name;

    public Address(Integer id, int name) {
      this.id = id;
      this.name = name;
    }

    @Override
    public String toString() {
      return "Address{" + "id=" + id + ", name=" + name + '}';
    }

    public Integer getId() {
      return id;
    }

    public void setId(int id) {
      this.id = id;
    }

    public int getName() {
      return name;
    }

    public void setName(int name) {
      this.name = name;
    }
  }
}
