package com.soa.lxf.controller;

import com.soa.lxf.exception.UserNotEixstException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: lxf
 * @create: 2020-04-02 11:09
 * @description: 用于处理所有controller抛出的异常
 */
@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(UserNotEixstException.class)
    @ResponseBody
    @ResponseStatus(code= HttpStatus.BAD_REQUEST)
    public Map<String,Object>  handlerException( UserNotEixstException ex){
        Map<String,Object> error=new HashMap<>();
        error.put("id",ex.getId());
        error.put("errorMessage",ex.getMessage());
        return error;
    }
}
