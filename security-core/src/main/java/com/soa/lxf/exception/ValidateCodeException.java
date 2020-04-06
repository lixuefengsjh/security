package com.soa.lxf.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @author: lxf
 * @create: 2020-04-05 23:22
 * @description: 验证码异常
 */
public class ValidateCodeException extends AuthenticationException {
    public ValidateCodeException(String msg) {
        super(msg);
    }
}
