package com.test.dao;

import com.test.entry.UserIn;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInRepository extends JpaRepository<UserIn, Long> {
  long countById(Long id);
}