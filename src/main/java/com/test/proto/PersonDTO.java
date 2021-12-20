package com.test.proto;

import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Objects;

/**
 * @author 靖鸿宣
 * @since 2021/10/11
 */
@NoArgsConstructor
public class PersonDTO {
  private Long id;
  private String name;
  /** 对应 Person.user.age */
  private Integer age;

  private Integer addressId;
  private Integer addressName;

  private String email;
  /** 与 DO 里面的字段名称(birthDay)不一致 */
  private Date birth;
  /** 对 DO 里面的字段(birthDay)进行拓展,dateFormat 的形式 */
  private String birthDateFormat;
  /** 对 DO 里面的字段(birthDay)进行拓展,expression 的形式 */
  private String birthExpressionFormat;

  public PersonDTO(
      Long id,
      String name,
      Integer age,
      Integer addressId,
      Integer addressName,
      String email,
      Date birth,
      String birthDateFormat,
      String birthExpressionFormat) {
    this.id = id;
    this.name = name;
    this.age = age;
    this.addressId = addressId;
    this.addressName = addressName;
    this.email = email;
    this.birth = birth;
    this.birthDateFormat = birthDateFormat;
    this.birthExpressionFormat = birthExpressionFormat;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public Integer getAddressId() {
    return addressId;
  }

  public void setAddressId(Integer addressId) {
    this.addressId = addressId;
  }

  public Integer getAddressName() {
    return addressName;
  }

  public void setAddressName(Integer addressName) {
    this.addressName = addressName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Date getBirth() {
    return birth;
  }

  public void setBirth(Date birth) {
    this.birth = birth;
  }

  public String getBirthDateFormat() {
    return birthDateFormat;
  }

  public void setBirthDateFormat(String birthDateFormat) {
    this.birthDateFormat = birthDateFormat;
  }

  public String getBirthExpressionFormat() {
    return birthExpressionFormat;
  }

  public void setBirthExpressionFormat(String birthExpressionFormat) {
    this.birthExpressionFormat = birthExpressionFormat;
  }

  @Override
  public String toString() {
    return "PersonDTO{"
        + "id="
        + id
        + ", name='"
        + name
        + '\''
        + ", age="
        + age
        + ", addressId="
        + addressId
        + ", addressName="
        + addressName
        + ", email='"
        + email
        + '\''
        + ", birth="
        + birth
        + ", birthDateFormat='"
        + birthDateFormat
        + '\''
        + ", birthExpressionFormat='"
        + birthExpressionFormat
        + '\''
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
    PersonDTO personDTO = (PersonDTO) o;
    return Objects.equals(id, personDTO.id)
        && Objects.equals(name, personDTO.name)
        && Objects.equals(age, personDTO.age)
        && Objects.equals(email, personDTO.email)
        && Objects.equals(birth, personDTO.birth)
        && Objects.equals(birthDateFormat, personDTO.birthDateFormat)
        && Objects.equals(birthExpressionFormat, personDTO.birthExpressionFormat);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, age, email, birth, birthDateFormat, birthExpressionFormat);
  }
}
