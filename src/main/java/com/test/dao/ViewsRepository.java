package com.test.dao;

import com.test.entry.Views;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ViewsRepository extends JpaRepository<Views, Long> {
  Optional<Views> findByYearAndMonthAndDate(Integer year, Integer month, Integer date);
}
