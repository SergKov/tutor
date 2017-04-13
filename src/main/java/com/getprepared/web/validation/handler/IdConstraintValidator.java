package com.getprepared.web.validation.handler;

import com.getprepared.web.validation.ConstraintValidator;
import com.getprepared.web.validation.annotation.Id;

import static org.apache.commons.lang3.StringUtils.isNumeric;

/**
 * Created by koval on 13.04.2017.
 */
public class IdConstraintValidator implements ConstraintValidator<Id, String> {

    @Override
    public boolean isValid(final String item) {
        return isNumeric(item) && isInRange(item);
    }

    @SuppressWarnings("all")
    private boolean isInRange(final String item) {
        try {
            Long.valueOf(item);
            return true;
        } catch (final NumberFormatException e) {
            return false;
        }
    }
}
