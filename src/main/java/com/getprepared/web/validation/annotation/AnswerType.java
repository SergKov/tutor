package com.getprepared.web.validation.annotation;

import com.getprepared.persistence.domain.Type;
import com.getprepared.web.validation.handler.AnswerTypeConstraintValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by koval on 21.03.2017.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AnswerTypeConstraintValidator.class,
            message = "constraint.message.answer.type")
public @interface AnswerType {

    Type[] value();
}
