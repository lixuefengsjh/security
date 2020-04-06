package com.soa.lxf.exception;

import lombok.Data;

/**
 * @author: lxf
 * @create: 2020-04-02 11:16
 * @description: 用户不存在异常
 */
@Data
public class UserNotEixstException extends RuntimeException {
   private  Long id ;
   public UserNotEixstException(Long id){
       super("User is not exsit");
       this.id=id;
   }


}
