package com.getprepared.web.validation.annotation;

import com.getprepared.web.validation.handler.LengthConstraintValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by koval on 11.04.2017.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = LengthConstraintValidator.class,
        message = "constraint.message.length")
public @interface Length {

    int max();
}
