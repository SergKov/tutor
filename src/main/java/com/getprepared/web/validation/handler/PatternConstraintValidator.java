package com.getprepared.web.validation.handler;

import com.getprepared.web.validation.annotation.Pattern;
import com.getprepared.web.validation.ConstraintValidator;

/**
 * Created by koval on 13.03.2017.
 */
public class PatternConstraintValidator implements ConstraintValidator<Pattern, String> {

    private Pattern annotation;

    @Override
    public void init(final Pattern annotation) {
        this.annotation = annotation;
    }

    @Override
    public boolean isValid(final String item) {
        final String regexp = annotation.regexp();
        return item.matches(regexp);
    }
}