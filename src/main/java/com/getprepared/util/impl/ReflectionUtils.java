package com.getprepared.util.impl;

import org.apache.log4j.Logger;

import java.lang.reflect.Field;

/**
 * Created by koval on 01.03.2017.
 */
public class ReflectionUtils {

    private static final Logger LOG = Logger.getLogger(ReflectionUtils.class);

    private ReflectionUtils() { }

    public static Object newInstance(final Class clazz) {
        try {
            return clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            final String errorMsg = String.format("Failed to instantiate class %s", clazz.getName());
            LOG.error(errorMsg, e);
            throw new IllegalStateException(errorMsg, e);
        }
    }

    public static Class getClassForName(final String name) {
        try {
            return Class.forName(name);
        } catch (final ClassNotFoundException e) {
            final String errorMsg = String.format("Failed to get class for name %s", name);
            LOG.error(errorMsg, e);
            throw new IllegalStateException(errorMsg, e);
        }
    }

    public static void setField(final Field field, final Object bean, final Object injectedValue) {
        field.setAccessible(true);
        try {
            field.set(bean, injectedValue);
        } catch (IllegalArgumentException | IllegalAccessException e) {
            final String errorMsg = String.format("Failed to initialize field %s", field.getName());
            LOG.error(errorMsg, e);
            throw new IllegalStateException(errorMsg, e);
        }
    }
}
