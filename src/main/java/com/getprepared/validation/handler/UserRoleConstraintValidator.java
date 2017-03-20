package com.getprepared.validation.handler;

import com.getprepared.annotation.UserRole;
import com.getprepared.validation.ConstraintValidator;

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
