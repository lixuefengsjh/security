package com.soa.lxf.aspect;

import com.soa.lxf.controller.UserController;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * @author: lxf
 * @create: 2020-04-02 18:53
 * @description: 切片
 */
@Aspect
@Component
public class TimeAspect {

    @Around("execution(* com.soa.lxf.controller.UserController.*(..))")
    public Object handleControllerMethod(ProceedingJoinPoint pjp) throws Throwable {

        System.out.println("time aspect start");

        Object[] args = pjp.getArgs();
        for (Object arg : args) {
            System.out.println("arg is "+arg);
        }

        LocalDateTime start = LocalDateTime.now();

        Object object = pjp.proceed();
        LocalDateTime end = LocalDateTime.now();
        System.out.println("time aspect 耗时:"+ Duration.between(start,end).toMillis());
        System.out.println("这是object 的真实值"+object);
        System.out.println("time aspect end");
        return object;
    }
}
