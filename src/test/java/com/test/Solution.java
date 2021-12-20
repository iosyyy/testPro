package com.test;

import java.util.Stack;

/**
 * @author 靖鸿宣
 * @since 2021/12/1
 */
public class Solution {
  public int[] dailyTemperatures(int[] T) {
    Stack<Integer> stack = new Stack<>();
    int[] res = new int[T.length];
    for (int i = 0; i < T.length; i++) {
      while (!stack.isEmpty() && T[stack.peek()] < T[i]) {
        int temp = stack.pop();
        res[temp] = i - temp;
      }

      stack.push(i);
    }
    return res;
  }
}
