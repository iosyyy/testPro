package com.test.handlers;

import com.test.utils.ReturnResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author authoa
 * @since 2021/9/28
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

  @ExceptionHandler(Exception.class)
  public ReturnResult runtimeExceptionHandler(Exception ex, HttpServletResponse response) {
    try {
      response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, ex.getMessage());
    } catch (IOException e) {
      e.printStackTrace();
    }
    return ReturnResult.builder().code(500).msg(ex.getMessage()).build();
  }
}
