package com.getprepared.infrastructure.context;

import com.getprepared.annotation.Bean;
import com.getprepared.annotation.Component;
import com.getprepared.annotation.Configuration;
import com.getprepared.annotation.Inject;
import com.getprepared.infrastructure.BeanFactory;
import com.getprepared.util.impl.PackageScanner;
import com.getprepared.util.impl.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by koval on 25.02.2017.
 */
public class ApplicationContext implements BeanFactory {

    private static final String PREFIX = "com.getprepared.";
    private static final String CONFIG_PACKAGE = PREFIX + "infrastructure.config";
    private static final String DAO_PACKAGE = PREFIX + "dao.impl";
    private static final String SERVICE_PACKAGE = PREFIX + "service";
    private static final String CONTROLLER_PACKAGE = PREFIX + "controller";

    private final Map<String, Object> container = new HashMap<>();

    public ApplicationContext() {
        loadConfig(CONFIG_PACKAGE);

        load(DAO_PACKAGE);
        load(SERVICE_PACKAGE);
        load(CONTROLLER_PACKAGE);

        injectFields();
    }

    private void loadConfig(final String packageName) {
        final List<Class<?>> classes = PackageScanner.scan(packageName);

        classes.forEach(clazz -> {
            if (clazz.isAnnotationPresent(Configuration.class)) {
                final Method[] methods = clazz.getDeclaredMethods();
                Arrays.stream(methods).forEach(method -> {
                    if (method.isAnnotationPresent(Bean.class)) {
                        final Bean annotation = method.getAnnotation(Bean.class);
                        final String beanName = annotation.value();
                        final Object config = ReflectionUtils.newInstance(clazz);
                        container.put(beanName, ReflectionUtils.invoke(method, config));
                    }
                });
            }
        });
    }

    private void load(final String packageName) {
        final List<Class<?>> classes = PackageScanner.scan(packageName);

        classes.forEach(clazz -> {
            if (clazz.isAnnotationPresent(Component.class)) {
                final Component annotation = (Component) clazz.getAnnotation(Component.class);
                final String beanName = annotation.value();
                container.put(beanName, ReflectionUtils.newInstance(clazz));
            }
        });
    }

    private void injectFields() {
        container.values().forEach(this::injectFields);
    }

    private void injectFields(final Object bean) {
        for (Class clazz = bean.getClass(); clazz != Object.class; clazz = clazz.getSuperclass()) {
            final Field[] fields = clazz.getDeclaredFields();
            Arrays.stream(fields).forEach(field -> {
                if (field.isAnnotationPresent(Inject.class)) {
                    final Object injectedValue = getBean(field.getName());
                    ReflectionUtils.setField(field, bean, injectedValue);
                }
            });
        }
    }

    @Override
    public Object getBean(final String name) {
        return container.get(name);
    }

    @Override
    public <T> T getBean(final String name, final Class<T> clazz) {
        final Object bean = container.get(name);
        return clazz.cast(bean);
    }
}
