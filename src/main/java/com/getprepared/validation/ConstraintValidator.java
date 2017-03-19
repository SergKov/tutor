package com.getprepared.validation;

import java.lang.annotation.Annotation;

/**
 * Created by koval on 13.03.2017.
 */
public interface ConstraintValidator<A extends Annotation, T> {

    default void init(A annotation) { }

    boolean isValid(T item);
}
