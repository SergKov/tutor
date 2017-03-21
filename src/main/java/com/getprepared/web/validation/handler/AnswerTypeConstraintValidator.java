package com.getprepared.web.validation.handler;

import com.getprepared.persistence.domain.Type;
import com.getprepared.web.validation.ConstraintValidator;
import com.getprepared.web.validation.annotation.AnswerType;

/**
 * Created by koval on 21.03.2017.
 */
public class AnswerTypeConstraintValidator implements ConstraintValidator<AnswerType, String> {

    private AnswerType annotation;

    @Override
    public void init(final AnswerType annotation) {
        this.annotation = annotation;
    }

    @Override
    public boolean isValid(final String item) {
        final Type[] types = annotation.value();
        for (final Type type : types) {
            if (type.name().equalsIgnoreCase(item)) {
                return true;
            }
        }
        return false;
    }
}
