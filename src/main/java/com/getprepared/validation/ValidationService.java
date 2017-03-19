package com.getprepared.validation;

import com.getprepared.annotation.Component;
import com.getprepared.annotation.Constraint;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static com.getprepared.util.ReflectionUtils.getFieldValue;
import static com.getprepared.util.ReflectionUtils.newInstance;
import static java.util.Arrays.stream;
import static java.util.Collections.emptyList;
import static org.apache.commons.lang3.ArrayUtils.isEmpty;

/**
 * Created by koval on 13.03.2017.
 */
@Component
public class ValidationService {

    public List<String> validate(final Object object) {
        final Field[] fields = object.getClass().getDeclaredFields();

        if (isEmpty(fields)) {
            return emptyList();
        }
        final List<String> errors = new ArrayList<>();
        stream(fields).forEach(field -> manageAnnotations(errors, field, object));

        return errors;
    }

    @SuppressWarnings("unchecked")
    private void manageAnnotations(final List<String> errors, final Field field, final Object object) {
        final Annotation[] annotations = field.getAnnotations();
        for (final Annotation annotation : annotations) {
            if (isConstraint(annotation)) {
                final ConstraintValidator validator = getConstraintValidator(annotation);
                validator.init(annotation);
                if (!validator.isValid(getFieldValue(field, object))) {
                    errors.add(getMessage(annotation));
                    break;
                }
            }
        }
    }

    private String getMessage(final Annotation annotation) {
        final Constraint constraint = annotation.annotationType().getAnnotation(Constraint.class);
        return constraint.message();
    }

    private ConstraintValidator getConstraintValidator(final Annotation annotation) {
        final Constraint constraint = annotation.annotationType().getAnnotation(Constraint.class);
        return newInstance(constraint.validatedBy());
    }

    private boolean isConstraint(final Annotation annotation) {
        final Annotation[] metaAnnotations = annotation.annotationType().getAnnotations();
        for (final Annotation metaAnnotation : metaAnnotations) {
            if (metaAnnotation.annotationType() == Constraint.class) {
                return true;
            }
        }
        return false;
    }
}
