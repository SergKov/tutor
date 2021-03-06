package com.getprepared.core.util;

import org.apache.log4j.Logger;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by koval on 01.03.2017.
 */
public final class ReflectionUtils {

    private static final Logger LOG = Logger.getLogger(ReflectionUtils.class);

    public ReflectionUtils() { }

    public <T> T newInstance(final Class<T> clazz) {
        try {
            return clazz.newInstance();
        } catch (final Exception e) {
            final String errorMsg = String.format("Failed to instantiate class %s", clazz.getName());
            LOG.error(errorMsg, e);
            throw new IllegalStateException(errorMsg, e);
        }
    }

    public Class<?> getClassForName(final String name) {
        try {
            return Class.forName(name);
        } catch (final ClassNotFoundException e) {
            final String errorMsg = String.format("Failed to get class for name %s", name);
            LOG.error(errorMsg, e);
            throw new IllegalStateException(errorMsg, e);
        }
    }

    public Object invoke(final Method method, final Object obj, final Object... args) {
        try {
            return method.invoke(obj, args);
        } catch (final Exception e) {
            final String errorMsg = String.format("Failed to invoke method %s", method.getName());
            LOG.error(errorMsg, e);
            throw new IllegalStateException(errorMsg, e);
        }
    }

    public Object getField(final Field field, final Object object) {
        field.setAccessible(true);
        try {
            return field.get(object);
        } catch (final Exception e) {
            final String errorMsg = String.format("Failed to get field %s", field.getName());
            LOG.error(errorMsg, e);
            throw new IllegalStateException(errorMsg, e);
        }
    }

    public void setField(final Field field, final Object bean, final Object injectedValue) {
        field.setAccessible(true);
        try {
            field.set(bean, injectedValue);
        } catch (final Exception e) {
            final String errorMsg = String.format("Failed to initialize field %s", field.getName());
            LOG.error(errorMsg, e);
            throw new IllegalStateException(errorMsg, e);
        }
    }
}
