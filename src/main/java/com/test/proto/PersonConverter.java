package com.test.proto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * @author 靖鸿宣
 * @since 2021/10/11
 */
@Mapper(componentModel = "spring")
public interface PersonConverter {
  @Mappings({
    @Mapping(source = "birthday", target = "birth"),
    @Mapping(source = "birthday", target = "birthDateFormat", dateFormat = "yyyy-MM-dd HH:mm:ss"),
    @Mapping(
        target = "birthExpressionFormat",
        expression =
            "java(org.apache.commons.lang3.time.DateFormatUtils.format(person.getBirthday(),\"yyyy-MM-dd HH:mm:ss\"))"),
    @Mapping(source = "user.age", target = "age"),
    @Mapping(target = "email", ignore = true),
    @Mapping(source = "address.id", target = "addressId", defaultValue = "15"),
    @Mapping(source = "address.name", target = "addressName")
  })
  PersonDTO domain2dto(Personal person);

  List<PersonDTO> domain2dto(List<Personal> people);

  Personal domain2dto(PersonDTO people);
}
