package com.test.proper;

import lombok.SneakyThrows;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

/**
 * @author 靖鸿宣
 * @since 2021/10/1
 */
@Async
@Component
public class TestAsnyc {
  @SneakyThrows
  public CompletableFuture<String> getHello() {
    Thread.sleep(3000);
    return CompletableFuture.completedFuture("hello world");
  }
}
