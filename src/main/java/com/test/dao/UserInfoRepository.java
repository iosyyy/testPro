package com.test.dao;

import com.test.entry.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
  List<UserInfo> findByUserIn_Id(Long id);

  @Override
  Optional<UserInfo> findById(Long aLong);
}
