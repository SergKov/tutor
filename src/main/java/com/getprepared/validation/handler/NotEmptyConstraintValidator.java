package com.getprepared.validation.handler;

import com.getprepared.annotation.NotEmpty;
import com.getprepared.validation.ConstraintValidator;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by koval on 12.03.2017.
 */
public class NotEmptyConstraintValidator implements ConstraintValidator<NotEmpty, String> {

    @Override
    public boolean isValid(final String item) {
        return StringUtils.isEmpty(item);
    }
}
