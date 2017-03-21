package com.getprepared.web.validation.annotation;

import com.getprepared.web.validation.handler.SizeConstraintValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by koval on 21.03.2017.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = SizeConstraintValidator.class,
            message = "constraint.message.size")
public @interface Size {

    int min();

    int max();
}
