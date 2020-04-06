package com.soa.lxf.configure;

import com.soa.lxf.controller.ValidateCodeController;
import com.soa.lxf.controller.ValidateCodeFilter;
import com.soa.lxf.properties.BrowserProperties;
import com.soa.lxf.properties.SecurityProperites;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author: lxf
 * @create: 2020-04-03 22:35
 * @description:
 */
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private SecurityProperites securityProperites;
    @Autowired
    private AuthenticationFailureHandler loginFailedController;
    @Autowired
    private AuthenticationSuccessHandler loginSuceessController;
    @Bean
    public PasswordEncoder  passwordEncoder(){
        return  new BCryptPasswordEncoder();
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        ValidateCodeFilter filter =new ValidateCodeFilter();
        filter.setAuthenticationFailureHandler(loginFailedController);
        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
                .formLogin()
                //自定义登陆页面
                .loginPage("/auth/require")
                .loginProcessingUrl("/login")
                .failureHandler(loginFailedController)
                .successHandler(loginSuceessController)
                .and()
                .authorizeRequests()
                .antMatchers("/auth/require",
                        securityProperites.getBrowser().getLoginPage(),
                        securityProperites.getBrowser().getLoginFailed(),
                        "/image/code"
                ).permitAll()
                .anyRequest()
                .authenticated()
                .and()
                //一定要加，否则会报302异常
                .csrf().disable();
    }
}
