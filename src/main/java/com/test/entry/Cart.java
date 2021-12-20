package com.test.entry;

import lombok.Builder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author authoa
 * @since 2021/9/28
 */
@Entity(name = "cart")
@Builder
public class Cart {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", nullable = false)
  private Long id;

  @CreationTimestamp
  @Column(nullable = false, updatable = false)
  private java.util.Date createdAt;

  @UpdateTimestamp
  @Column(nullable = false)
  private java.util.Date updatedAt;

  @OneToOne(orphanRemoval = true)
  @JoinColumn(name = "user_id")
  private UserIn user;

  @ManyToMany
  @JoinTable(
      name = "cart_heas",
      joinColumns = @JoinColumn(name = "cart_id"),
      inverseJoinColumns = @JoinColumn(name = "heas_id"))
  private List<Hea> heas = new ArrayList<>();

  public Cart(Long id, Date createdAt, Date updatedAt, UserIn user, List<Hea> heas) {
    this.id = id;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
    this.user = user;
    this.heas = heas;
  }

  public Cart() {}

  public UserIn getUser() {
    return user;
  }

  public void setUser(UserIn user) {
    this.user = user;
  }

  public List<Hea> getHeas() {
    return heas;
  }

  public void setHeas(List<Hea> heas) {
    this.heas = heas;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }

  public Date getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(Date updatedAt) {
    this.updatedAt = updatedAt;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }
}
