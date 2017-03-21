package com.getprepared.web.validation.annotation;

import com.getprepared.persistence.domain.Role;
import com.getprepared.web.validation.handler.UserRoleConstraintValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by koval on 20.03.2017.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UserRoleConstraintValidator.class,
            message = "constraint.message.user.role")
public @interface UserRole {

    Role[] value();
}
