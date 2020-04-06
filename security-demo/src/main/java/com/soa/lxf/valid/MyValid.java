package com.soa.lxf.valid;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotBlank;
import java.lang.annotation.*;

@Documented
@Constraint(
        validatedBy = {MyValidimpl.class}
)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
public @interface MyValid {
    String message() default "{javax.validation.constraints.MyValid.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
