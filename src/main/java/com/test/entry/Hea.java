package com.test.entry;

import lombok.Builder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

/**
 * @author authoa
 * @since 2021/9/28
 */
@Entity(name = "hea")
@Builder
public class Hea {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", nullable = false)
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "price")
  private Long price;

  @Column(name = "stock")
  private Integer stock;

  @Column(name = "image_url")
  private String imageUrl;

  @Column(name = "describew")
  private String describe;

  @Column(name = "factory")
  private String factory;

  @CreationTimestamp
  @Column(nullable = false, updatable = false)
  private java.util.Date createdAt;

  @UpdateTimestamp
  @Column(nullable = false)
  private java.util.Date updatedAt;

  public Hea() {}

  public Hea(
      Long id,
      String name,
      Long price,
      Integer stock,
      String imageUrl,
      String describe,
      String factory,
      Date createdAt,
      Date updatedAt) {
    this.id = id;
    this.name = name;
    this.price = price;
    this.stock = stock;
    this.imageUrl = imageUrl;
    this.describe = describe;
    this.factory = factory;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

  public String getFactory() {
    return factory;
  }

  public void setFactory(String factory) {
    this.factory = factory;
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

  public String getDescribe() {
    return describe;
  }

  public void setDescribe(String describe) {
    this.describe = describe;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  public Integer getStock() {
    return stock;
  }

  public void setStock(Integer stock) {
    this.stock = stock;
  }

  public Long getPrice() {
    return price;
  }

  public void setPrice(Long price) {
    this.price = price;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }
}
