package com.getprepared.validation.handler;

import com.getprepared.annotation.NotEmpty;
import com.getprepared.validation.ConstraintValidator;

import static org.apache.commons.lang3.StringUtils.isEmpty;

/**
 * Created by koval on 12.03.2017.
 */
public class NotEmptyConstraintValidator implements ConstraintValidator<NotEmpty, String> {

    @Override
    public boolean isValid(final String item) {
        return isEmpty(item);
    }
}
