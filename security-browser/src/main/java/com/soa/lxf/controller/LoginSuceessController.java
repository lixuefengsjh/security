package com.soa.lxf.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.soa.lxf.entity.LoginResponseType;
import com.soa.lxf.properties.SecurityProperites;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: lxf
 * @create: 2020-04-05 11:01
 * @description: 登陆成功的处理
 */
@Component
@Slf4j
public class LoginSuceessController implements AuthenticationSuccessHandler {
    @Autowired
    private ObjectMapper  objectMapper;
    @Autowired
    private SecurityProperites securityProperites;

    private RedirectStrategy redirectStrategy =new DefaultRedirectStrategy();
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("登陆成功");
        if(LoginResponseType.JSON.equals(securityProperites.getType())){
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(authentication));
        }else {
            redirectStrategy.sendRedirect(request,response,securityProperites.getBrowser().getLoginSuccess());
        }


    }
}
