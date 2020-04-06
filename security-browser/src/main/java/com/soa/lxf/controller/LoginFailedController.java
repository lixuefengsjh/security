package com.soa.lxf.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.soa.lxf.entity.LoginResponseType;
import com.soa.lxf.properties.SecurityProperites;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: lxf
 * @create: 2020-04-05 11:08
 * @description: 登陆失败的处理
 */
@Component(value="authenticationFailureHandler")
@Slf4j
public class LoginFailedController implements AuthenticationFailureHandler {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private SecurityProperites  securityProperites;

    private RedirectStrategy redirectStrategy =new DefaultRedirectStrategy();
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        log.info("登陆失败");
        if(LoginResponseType.JSON.equals(securityProperites.getType())){
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(e));
        }else {
            redirectStrategy.sendRedirect(request,response,securityProperites.getBrowser().getLoginFailed());
        }

    }
}
