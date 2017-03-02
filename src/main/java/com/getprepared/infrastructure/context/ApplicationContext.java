package com.getprepared.infrastructure.context;

import com.getprepared.annotation.Bean;
import com.getprepared.annotation.Inject;
import com.getprepared.infrastructure.BeanFactory;
import com.getprepared.util.impl.ReflectionUtils;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import java.lang.reflect.Field;
import java.util.*;

/**
 * Created by koval on 25.02.2017.
 */
public class ApplicationContext implements BeanFactory {

    private static final String PACKAGE = "com.getprepared";

    private final Map<String, Object> map = new HashMap<>();

    public ApplicationContext() {
        load(PACKAGE);
        injectFields();
    }

    private void load(final String packageName) {
        final Reflections reflections = new Reflections(packageName, new SubTypesScanner(false));
        final Collection<Class<?>> classes = reflections.getSubTypesOf(Object.class);

        classes.forEach(clazz -> {
            if (clazz.isAnnotationPresent(Bean.class)) {
                final Bean annotation = (Bean) clazz.getAnnotation(Bean.class);
                map.put(annotation.value(), ReflectionUtils.newInstance(clazz));
            }
        });
    }

    private void injectFields() {
        map.values().forEach(bean -> injectFields(bean.getClass()));
    }

    private void injectFields(final Class<?> beanClass) {
        final Field[] fields = beanClass.getDeclaredFields();
        Arrays.stream(fields).forEach(field -> {
            if (field.isAnnotationPresent(Inject.class)) {
                final Object injectedValue = getBean(field.getName());
                ReflectionUtils.setField(field, beanClass, injectedValue);
            }
        });

        final Set<Class> checkedClasses = new HashSet<>();
        final Class superClass = beanClass.getClass().getSuperclass();
        if (superClass != Object.class && !checkedClasses.contains(superClass)
                && !superClass.isAnnotationPresent(Bean.class)) {
            checkedClasses.add(superClass);
            injectFields(superClass);
        }
    }

    @Override
    public Object getBean(final String name) {
        return map.get(name);
    }

    @Override
    public <T> T getBean(final String name, final Class<T> clazz) {
        final Object bean = map.get(name);
        return clazz.cast(bean);
    }
}
