package com.getprepared.web.validation.handler;

import com.getprepared.web.validation.ConstraintValidator;
import com.getprepared.web.validation.annotation.Size;

/**
 * Created by koval on 21.03.2017.
 */
public class SizeConstraintValidator implements ConstraintValidator<Size, int[]> {

    private Size annotation;

    @Override
    public void init(final Size annotation) {
        this.annotation = annotation;
    }

    @Override
    public boolean isValid(final int[] item) {
        final int min = annotation.min();
        final int max = annotation.max();
        return item.length >= min && item.length <= max;
    }
}
