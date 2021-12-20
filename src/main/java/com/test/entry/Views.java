package com.test.entry;

import lombok.Builder;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author 靖鸿宣
 * @since 2021/12/19
 */
@Entity(name = "views")
@Builder
public class Views {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", nullable = false)
  private Long id;

  @Column(name = "year")
  private Integer year;

  @Column(name = "month")
  private Integer month;

  @Column(name = "date")
  private Integer date;

  @Column(name = "count")
  private Integer count;

  public Views(Long id, Integer year, Integer month, Integer date, Integer count) {
    this.id = id;
    this.year = year;
    this.month = month;
    this.date = date;
    this.count = count;
  }

  public Views() {}

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Views views = (Views) o;
    return Objects.equals(id, views.id)
        && Objects.equals(year, views.year)
        && Objects.equals(month, views.month)
        && Objects.equals(date, views.date)
        && Objects.equals(count, views.count);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, year, month, date, count);
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Integer getYear() {
    return year;
  }

  public void setYear(Integer year) {
    this.year = year;
  }

  public Integer getMonth() {
    return month;
  }

  public void setMonth(Integer month) {
    this.month = month;
  }

  public Integer getDate() {
    return date;
  }

  public void setDate(Integer date) {
    this.date = date;
  }

  public Integer getCount() {
    return count;
  }

  public void setCount(Integer count) {
    this.count = count;
  }
}
