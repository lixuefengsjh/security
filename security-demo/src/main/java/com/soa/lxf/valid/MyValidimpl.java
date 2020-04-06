package com.soa.lxf.valid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author: lxf
 * @create: 2020-03-31 23:15
 * @description:
 */
public class MyValidimpl implements ConstraintValidator<MyValid,Object> {
    /**
     * 这里我们可以使用autowired注入任何的组件，注意MyValidimpl 这个不必家@compent标签，因为ConstraintValidator自己本身含有
     * @param constraintAnnotation
     */
    @Override
    public void initialize(MyValid constraintAnnotation) {
        System.out.println("开始初始化");
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        System.out.println(o);
        String name =(String)o;
        return !name.equals("lxf");
    }
}
