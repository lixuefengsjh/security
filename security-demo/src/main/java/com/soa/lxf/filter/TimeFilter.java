package com.soa.lxf.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;

/**
 * @author: lxf
 * @create: 2020-04-02 12:06
 * @description:
 */

/**
 * 2点思考：
 * 1）如果是第三方的Filter怎么变成bean注解
 * 2）如何拦截特定的url请求
 */
//@Component
public class TimeFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("TimeFilter is init----");
    }

    @Override
    public void doFilter(ServletRequest re, ServletResponse rs, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("TimeFilter is start----");
        LocalDateTime  begin =LocalDateTime.now();
        filterChain.doFilter(re,rs);
        LocalDateTime  end =LocalDateTime.now();
        System.out.println("执行玩方法耗时为："+ Duration.between(begin,end).toMillis() );
        System.out.println("TimeFilter is finished----");
    }

    @Override
    public void destroy() {
        System.out.println("TimeFilter is destroy---");
    }
}
