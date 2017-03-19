package com.getprepared.annotation;

import com.getprepared.validation.handler.NotEmptyConstraintValidator;

import java.lang.annotation.*;

/**
 * Created by koval on 12.03.2017.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NotEmptyConstraintValidator.class,
            message = "constraint.message.notEmpty")
public @interface NotEmpty { }
