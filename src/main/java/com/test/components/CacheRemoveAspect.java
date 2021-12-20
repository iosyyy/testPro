package com.test.components;

import com.google.common.base.Strings;
import com.test.annotation.CacheRemove;
import com.test.dao.ViewsRepository;
import com.test.entry.Views;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.Objects;
import java.util.Optional;

/**
 * TODO 想了一下要写这东西改的有点多,等后续更新,目前只是删除缓存中所有的数据
 *
 * @author 靖鸿宣
 * @since 2021/10/1
 */
@Component
@Aspect
@Slf4j
public class CacheRemoveAspect {

  @Resource CacheManager cacheManager;
  @Resource ViewsRepository viewsRepository;

  @Pointcut("execution(* *.*(..)) && @annotation(com.test.annotation.CacheRemove)")
  public void pointcut() {}

  @Pointcut("execution(* com.test.controller.AuthController.login(..))")
  public void test() {}

  @Around("test()")
  public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
    Object proceed = joinPoint.proceed();
    log.info("切面获取返回值完成proceed:[{}]", proceed);
    return proceed;
  }

  @Before("test()")
  public void doBefore(JoinPoint joinPoint) {

    // 获取签名
    Signature signature = joinPoint.getSignature();
    MethodSignature signature1 = (MethodSignature) joinPoint.getSignature();
    // 获取切入的包名
    String declaringTypeName = signature.getDeclaringTypeName();
    // 获取即将执行的方法名
    String funcName = signature.getName();
    log.info("即将执行方法为: {}，属于{}包", funcName, declaringTypeName);
    // 也可以用来记录一些信息，比如获取请求的 URL 和 IP
    ServletRequestAttributes requestAttributes =
        (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
    requestAttributes.getRequest();
    ServletRequestAttributes attributes =
        (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    HttpServletRequest request = attributes.getRequest();
    // 获取请求 URL
    String url = request.getRequestURL().toString();
    String requestURI = request.getRequestURI();
    // 获取请求 IP
    String ip = request.getRemoteAddr();
    log.info("用户请求的url为：{}，ip地址为：{}", requestURI, ip);
    Calendar calendar = Calendar.getInstance();

    Optional<Views> views =
        viewsRepository.findByYearAndMonthAndDate(
            calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE));
    if (views.isPresent()) {
      Views views1 = views.get();
      Integer count = views1.getCount();
      if (count == null) {
        views1.setCount(0);
      } else {
        views1.setCount(count + 1);
      }
    } else {
      Views build =
          Views.builder()
              .month(calendar.get(Calendar.MONTH))
              .year(calendar.get(Calendar.YEAR))
              .date(calendar.get(Calendar.DATE))
              .count(0)
              .build();
      viewsRepository.save(build);
    }
  }

  @AfterReturning("pointcut()")
  public void removeAll(JoinPoint joinpoint) {
    MethodSignature signature = (MethodSignature) joinpoint.getSignature();
    Method method = signature.getMethod();

    CacheRemove annotation = method.getAnnotation(CacheRemove.class);
    if (!Objects.isNull(annotation)) {
      String value = annotation.value();
      if (!Strings.isNullOrEmpty(value)) {
        Cache cache = cacheManager.getCache(value);
        String[] key = annotation.key();
        if (!Objects.isNull(cache)) {
          if (key.length == 0) {
            cache.clear();
          }
        }
      }
    }
  }
}
