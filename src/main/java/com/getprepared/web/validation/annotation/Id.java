package com.getprepared.web.validation.annotation;

import com.getprepared.web.validation.handler.NotEmptyConstraintValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by koval on 13.04.2017.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NotEmptyConstraintValidator.class,
        message = "constraint.message.id")
public @interface Id { }
