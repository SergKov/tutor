package com.getprepared.web.validation.annotation;

import com.getprepared.web.validation.ConstraintValidator;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by koval on 18.03.2017.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Constraint {

    Class<? extends ConstraintValidator<?, ?>> validatedBy();

    String message();
}
