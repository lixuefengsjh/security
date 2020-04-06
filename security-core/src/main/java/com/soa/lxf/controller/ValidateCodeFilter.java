package com.soa.lxf.controller;

import com.soa.lxf.entity.ImageCode;
import com.soa.lxf.exception.ValidateCodeException;
import lombok.Data;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * @author: lxf
 * @create: 2020-04-05 23:13
 * @description: 图片验证过滤器
 */
@Data
public class ValidateCodeFilter  extends OncePerRequestFilter {

    private AuthenticationFailureHandler authenticationFailureHandler;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String uri=request.getRequestURI();
        if(StringUtils.equals(uri,"/login")){
            try {
                validate(request);
            }catch (ValidateCodeException ex){
                authenticationFailureHandler.onAuthenticationFailure(request,response,ex);
                return;
            }
        }
        filterChain.doFilter(request,response);
    }
    private void validate(HttpServletRequest request){
        String imageCode =request.getParameter("imageCode");
        ImageCode code =(ImageCode)request.getSession().getAttribute("code");
        if(!StringUtils.isNotBlank(imageCode)){
            throw  new ValidateCodeException("验证码为空");
        }else if(LocalDateTime.now().isAfter(code.getExpireTime())){
            throw  new ValidateCodeException("验证码过期");
        }else if(!StringUtils.equals(imageCode,code.getCode())){
            throw  new ValidateCodeException("验证码不匹配");
        }
    }

}
