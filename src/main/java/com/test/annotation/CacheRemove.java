package com.test.annotation;

/**
 * @author 靖鸿宣
 * @since 2021/10/1
 */
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CacheRemove {
  /**
   * 删除cacheNames
   *
   * @return
   */
  String value();

  /**
   * 删除某个缓存的某个key
   *
   * @return
   */
  String[] key();
}
