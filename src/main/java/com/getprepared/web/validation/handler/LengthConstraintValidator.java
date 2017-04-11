package com.getprepared.web.validation.handler;

import com.getprepared.web.validation.ConstraintValidator;
import com.getprepared.web.validation.annotation.Length;

/**
 * Created by koval on 11.04.2017.
 */
public class LengthConstraintValidator implements ConstraintValidator<Length, String> {

    private Length length;

    @Override
    public void init(final Length annotation) {
        this.length = length;
    }

    @Override
    public boolean isValid(final String item) {
        return item.length() < length.max();
    }
}
