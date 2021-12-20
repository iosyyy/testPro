package com.test;

import com.google.common.base.CharMatcher;
import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.collect.*;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author authoa
 * @since 2021/9/25
 */
public class Tests {
  @Test
  @SneakyThrows
  public void testAll() {
    List<Integer> list = new ArrayList<>();
    Optional<Integer> first = list.stream().findFirst();
    System.out.println(first.isPresent());
  }

  @Test
  @SneakyThrows
  public void test() {
    Preconditions.checkArgument(true, "hello world");
    Optional<Integer> integer = Optional.empty();
    Optional<Integer> val = Optional.of(5);
    //    System.out.println(integer.isPresent());
    //    System.out.println(val.isPresent());
    //    System.out.println(val.get());
    ComparisonChain start = ComparisonChain.start();
    ImmutableSet<Object> hello_world =
        ImmutableSet.builder().add("hello world").build(); // 使用builder方法创建一个不可变的set
    ImmutableList immutableList = ImmutableList.copyOf(hello_world); // 从不可变set中复制一个collection
    Multiset<Object> objects = HashMultiset.create(hello_world);
    objects.add("hello world");
    objects.add("hello world");
    objects.add("hello world");
    objects.add("hello world");
    objects.add("hello world");
    //    System.out.println(objects.count("hello world"));
    //    System.out.println(objects.count("hello"));
    ArrayListMultimap arrayListMultimap = ArrayListMultimap.create();
    arrayListMultimap.put("hello", Lists.newArrayList());

    String join = Joiner.on(";").useForNull("world").join("hello级急急急", null, "hello", "null");
    System.out.println(join);
    CharMatcher ascii = CharMatcher.ascii();
    List<String> strings =
        Splitter.on(",").trimResults(ascii).splitToList(join);
    System.out.println(strings);

    //    System.out.println(Lists.newArrayListWithCapacity(100).size());
    //    System.out.println(Lists.newArrayListWithExpectedSize(100).size());
    //    System.out.println(arrayListMultimap.get("hello").add("hello"));
    //    System.out.println(arrayListMultimap.get("hello"));
    //    System.out.println(
    //        start
    //            .compare(
    //                null,
    //                3,
    //                Ordering.natural()
    //                    .onResultOf(
    //                        new Function<Integer, Integer>() {
    //                          @Override
    //                          public @Nullable Integer apply(@Nullable Integer integer) {
    //                            assert integer != null;
    //                            return integer;
    //                          }
    //                        })
    //                    .nullsLast())// 给null设置一个权重
    //            .result());
  }
}
