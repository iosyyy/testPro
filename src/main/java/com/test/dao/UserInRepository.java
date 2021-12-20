package com.test.dao;

import com.test.entry.UserIn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface UserInRepository extends JpaRepository<UserIn, Long> {

  UserIn findByUserNameAndPassWord(String userName, String passWord);

  @Query(
      value = "select id, user_name, icon, is_admin, nick_name, is_cold, descition from user",
      nativeQuery = true)
  List<Map<Object, Object>> findAllUser();

  long countById(Long id);
}
