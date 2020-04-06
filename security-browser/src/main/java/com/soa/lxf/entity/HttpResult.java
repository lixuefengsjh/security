package com.soa.lxf.entity;
import lombok.AllArgsConstructor;
import lombok.Data;


import java.io.Serializable;

/**
 * @author: lxf
 * @create: 2020-03-03 11:34
 * @description: 响应数据结构
 */
@Data
@AllArgsConstructor
public class HttpResult<T> implements Serializable {

    private boolean success;

    private T result;

    private String error;

    public static HttpResult ok(Object t){
        return  new HttpResult(true,t,"数据返回成功");
    };
    public static HttpResult fail (){
        return  new HttpResult(false,null,"无法成功获取到数据");
    };
}
