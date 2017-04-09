package com.getprepared.web.validation.handler;

import com.getprepared.persistence.domain.Role;
import com.getprepared.web.validation.ConstraintValidator;
import com.getprepared.web.validation.annotation.UserRole;

import java.util.Objects;

/**
 * Created by koval on 20.03.2017.
 */
public class UserRoleConstraintValidator implements ConstraintValidator<UserRole, String> {

    private UserRole annotation;

    @Override
    public void init(final UserRole annotation) {
        this.annotation = annotation;
    }

    @Override
    public boolean isValid(final String item) {
        final Role[] roles = annotation.value();
        for (final Role role : roles) {
            if (role.name().equalsIgnoreCase(item)) {
                return true;
            }
        }
        return false;
    }
}
