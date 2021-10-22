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
public class PersonDTO {
  private Long id;
  private String name;
  /** 对应 Person.user.age */
  private Integer age;

  private String email;
  /** 与 DO 里面的字段名称(birthDay)不一致 */
  private Date birth;
  /** 对 DO 里面的字段(birthDay)进行拓展,dateFormat 的形式 */
  private String birthDateFormat;
  /** 对 DO 里面的字段(birthDay)进行拓展,expression 的形式 */
  private String birthExpressionFormat;

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
