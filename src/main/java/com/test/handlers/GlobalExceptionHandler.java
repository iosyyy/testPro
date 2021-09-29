package com.test.handlers;

import com.test.utils.RETURNCODE;
import com.test.utils.ReturnResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

/**
 * @author 靖鸿宣
 * @since 2021/9/28
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

  @ExceptionHandler(Exception.class)
  public ReturnResult runtimeExceptionHandler(Exception ex) {

    return ReturnResult.builder()
        .code(RETURNCODE.SERVER_ERROR.getCode())
        .msg(RETURNCODE.SERVER_ERROR.getMessage())
        .data(
            new HashMap<String, Object>() {
              {
                put("error", ex);
              }
            })
        .build();
  }
}
