package com.getprepared.web.validation.handler;

import com.getprepared.persistence.domain.Type;
import com.getprepared.web.validation.ConstraintValidator;
import com.getprepared.web.validation.annotation.AnswerType;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.stream;

/**
 * Created by koval on 21.03.2017.
 */
public class AnswerTypeConstraintValidator implements ConstraintValidator<AnswerType, String[]> {

    private AnswerType annotation;

    @Override
    public void init(final AnswerType annotation) {
        this.annotation = annotation;
    }

    @Override
    public boolean isValid(final String[] items) {
        final Type[] types = annotation.value();
        final List<String> stringType = convertToStringArray(types);

        for (final String item : items) {
            if (!stringType.contains(item.toUpperCase())) {
                return false;
            }
        }
        return true;
    }

    private List<String> convertToStringArray(final Type[] types) {
        final ArrayList<String> stringType = new ArrayList<>();
        stream(types).forEach(type -> stringType.add(type.name()));
        return stringType;
    }
}
