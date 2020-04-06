package com.soa.lxf.controller;

import com.soa.lxf.entity.HttpResult;
import com.soa.lxf.properties.SecurityProperites;
import com.sun.deploy.net.HttpResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: lxf
 * @create: 2020-04-05 10:10
 * @description: 登陆验证基本处理
 */
@RestController
public class BrowserSecurityController {

    private RequestCache requestCache=new HttpSessionRequestCache();
    private RedirectStrategy redirectStrategy=new DefaultRedirectStrategy();
    @Autowired
    private SecurityProperites securityProperites;
    @GetMapping("/auth/require")
    @ResponseStatus(code= HttpStatus.UNAUTHORIZED)
    public HttpResult requireAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SavedRequest savedRequest=requestCache.getRequest(request,  response);
        if(savedRequest!=null){
            String url=savedRequest.getRedirectUrl();
            if(StringUtils.endsWithIgnoreCase(url,".html")){
                redirectStrategy.sendRedirect(request,  response,securityProperites.getBrowser().getLoginPage());
            }
        }
        return new HttpResult(false,"需要权限认证","需要权限认证");
    }

}
