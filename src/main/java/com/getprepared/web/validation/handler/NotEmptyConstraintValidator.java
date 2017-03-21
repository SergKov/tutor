package com.getprepared.web.validation.handler;

import com.getprepared.web.validation.ConstraintValidator;
import com.getprepared.web.validation.annotation.NotEmpty;

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
