package com.soa.lxf.entity;

/**
 * @author: lxf
 * @create: 2020-04-05 11:26
 * @description: 登陆成功的跳转方式，这里其实意义不大。现阶段大多都是json
 */
public enum LoginResponseType {
    /**
     * 跳转
     */
    REDIRECT,

    /**
     * 返回json
     */
    JSON
}
