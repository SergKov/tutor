package com.getprepared.util;

import org.apache.log4j.Logger;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by koval on 01.03.2017.
 */
public class ReflectionUtils {

    private static final Logger LOG = Logger.getLogger(ReflectionUtils.class);

    private ReflectionUtils() { }

    public static <T> T newInstance(final Class<T> clazz) {
        try {
            return clazz.newInstance();
        } catch (final Exception e) {
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

    public static Object invoke(final Method method, final Object obj, final Object... args) {
        try {
            return method.invoke(obj, args);
        } catch (final Exception e) {
            final String errorMsg = String.format("Failed to invoke method %s", method.getName());
            LOG.error(errorMsg, e);
            throw new IllegalStateException(errorMsg, e);
        }
    }

    public static void setField(final Field field, final Object bean, final Object injectedValue) {
        setAccessibleTrue(field);
        try {
            field.set(bean, injectedValue);
        } catch (final Exception e) {
            final String errorMsg = String.format("Failed to initialize field %s", field.getName());
            LOG.error(errorMsg, e);
            throw new IllegalStateException(errorMsg, e);
        }
    }

    public static Object getFieldValue(final Field field, final Object object) {
        setAccessibleTrue(field);
        try {
            return field.get(object);
        } catch (final Exception e) {
            final String errorMsg = String.format("Failed to get field %s", field.getName());
            LOG.error(errorMsg, e);
            throw new IllegalStateException(errorMsg, e);
        }
    }

    private static void setAccessibleTrue(final Field field) {
        if (!field.isAccessible()) {
            field.setAccessible(true);
        }
    }
}
