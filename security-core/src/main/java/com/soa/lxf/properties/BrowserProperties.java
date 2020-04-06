package com.soa.lxf.properties;

import lombok.Data;

/**
 * @author: lxf
 * @create: 2020-04-05 09:51
 * @description: 浏览器相关的配置项目
 */
@Data
public class BrowserProperties {
    private String loginPage = "/signin.html";
    private String loginSuccess = "/index.html";
    private String loginFailed = "/error.html";
}
