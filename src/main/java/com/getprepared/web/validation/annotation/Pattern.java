package com.getprepared.web.validation.annotation;

import com.getprepared.web.validation.handler.PatternConstraintValidator;

import java.lang.annotation.*;

/**
 * Created by koval on 12.03.2017.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PatternConstraintValidator.class,
            message = "constraint.message.pattern")
public @interface Pattern {

    String regexp();
}
