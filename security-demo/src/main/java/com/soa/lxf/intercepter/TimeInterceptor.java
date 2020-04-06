package com.soa.lxf.intercepter;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.Temporal;

/**
 * @author: lxf
 * @create: 2020-04-02 15:32
 * @description:
 */
@Component
public class TimeInterceptor implements HandlerInterceptor {
  @Override
  public  boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
      System.out.println("preHandle===================");
      System.out.println(handler);
      request.setAttribute("startTime", LocalDateTime.now());
        return true;
    }

    @Override
    public  void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle===================");
        System.out.println("方法耗时"+ Duration.between((Temporal) request.getAttribute("startTime"),LocalDateTime.now()).toMillis());
  }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        System.out.println("afterCompletion===================");
        //这里ex为null因为ExceptionController会由于afterCompletion执行，将ex首先捕获
        //同时我们发现TimeInterceptor 会拦截所有的controller，包含spring内部的controller
        System.out.println(ex);
        System.out.println("方法耗时"+ Duration.between((Temporal) request.getAttribute("startTime"),LocalDateTime.now()).toMillis());
  }
}
