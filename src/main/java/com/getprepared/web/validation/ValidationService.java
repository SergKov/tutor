package com.getprepared.web.validation;

import com.getprepared.annotation.Component;
import com.getprepared.web.validation.annotation.Constraint;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import static com.getprepared.core.util.ReflectionUtils.getField;
import static com.getprepared.core.util.ReflectionUtils.newInstance;
import static java.util.Arrays.stream;
import static java.util.Collections.emptyMap;
import static org.apache.commons.lang3.ArrayUtils.isEmpty;

/**
 * Created by koval on 13.03.2017.
 */
@Component
public class ValidationService {

    public Map<String, String> validate(final Object object) {
        final Field[] fields = object.getClass().getDeclaredFields();

        if (isEmpty(fields)) {
            return emptyMap();
        }
        final Map<String, String> errors = new HashMap<>();
        stream(fields).forEach(field -> manageAnnotations(errors, field, object));

        return errors;
    }

    @SuppressWarnings("unchecked")
    private void manageAnnotations(final Map<String, String> errors, final Field field, final Object object) {
        final Annotation[] annotations = field.getAnnotations();
        for (final Annotation annotation : annotations) {
            if (isConstraint(annotation)) {
                final ConstraintValidator validator = getConstraintValidator(annotation);
                validator.init(annotation);
                if (!validator.isValid(getField(field, object))) {
                    errors.put(field.getName(), getMessage(annotation));
                    break;
                }
            }
        }
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

    private ConstraintValidator getConstraintValidator(final Annotation annotation) {
        final Constraint constraint = annotation.annotationType().getAnnotation(Constraint.class);
        return newInstance(constraint.validatedBy());
    }

    private String getMessage(final Annotation annotation) {
        final Constraint constraint = annotation.annotationType().getAnnotation(Constraint.class);
        return constraint.message();
    }
}
