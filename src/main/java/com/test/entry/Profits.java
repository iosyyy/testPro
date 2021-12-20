package com.test.entry;

import lombok.Builder;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

/**
 * @author 靖鸿宣
 * @since 2021/12/19
 */
@Entity(name = "profits")
@Builder
public class Profits {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", nullable = false)
  private Long id;

  @Column(name = "time")
  private Date time;

  @Column(name = "count")
  private Integer count;

  public Profits(Long id, Date time, Integer count) {
    this.id = id;
    this.time = time;
    this.count = count;
  }

  public Profits() {}

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Profits profits = (Profits) o;
    return Objects.equals(id, profits.id)
        && Objects.equals(time, profits.time)
        && Objects.equals(count, profits.count);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, time, count);
  }

  public Integer getCount() {
    return count;
  }

  public void setCount(Integer count) {
    this.count = count;
  }

  public Date getTime() {
    return time;
  }

  public void setTime(Date time) {
    this.time = time;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }
}
