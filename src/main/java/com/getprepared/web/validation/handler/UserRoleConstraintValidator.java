package com.getprepared.web.validation.handler;

import com.getprepared.web.validation.annotation.UserRole;
import com.getprepared.web.validation.ConstraintValidator;

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
        final Enum[] enums = annotation.value();
        for (final Enum elem : enums) {
            if (elem.name().equalsIgnoreCase(item)) {
                return true;
            }
        }
        return false;
    }
}
