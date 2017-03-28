package com.getprepared.context;

import com.getprepared.annotation.Bean;
import com.getprepared.annotation.Component;
import com.getprepared.annotation.Configuration;
import com.getprepared.annotation.Inject;
import com.getprepared.core.util.PackageScanner;
import com.getprepared.core.util.PropertyUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

import static com.getprepared.core.constant.PropertyConstant.FILES_NAME.COMPONENT_FILE;
import static com.getprepared.core.constant.PropertyConstant.FILES_NAME.CONFIGURATION_FILE;
import static com.getprepared.core.constant.ServerConstant.EMPTY_STRING;
import static com.getprepared.core.util.ReflectionUtils.invoke;
import static com.getprepared.core.util.ReflectionUtils.newInstance;
import static com.getprepared.core.util.ReflectionUtils.setField;
import static java.util.Arrays.stream;
import static org.apache.commons.lang3.StringUtils.uncapitalize;

/**
 * Created by koval on 25.02.2017.
 */
public class ApplicationContext {

    private static final String APPLICATION_CONTEXT = "applicationContext";

    private final Map<String, Object> container = new HashMap<>();

    ApplicationContext() { }

    void init() {
        container.put(APPLICATION_CONTEXT, this);
        initConfig();
        initComponent();
        injectFields();
    }

    private void initConfig() {
        final Properties configurationProp = PropertyUtils.getProperty(CONFIGURATION_FILE);
        final Set<Object> keys = configurationProp.keySet();

        keys.stream()
                .map(key -> (String) key)
                .forEach(key -> loadConfig(configurationProp.getProperty(key)));
    }

    private void initComponent() {
        final Properties componentProp = PropertyUtils.getProperty(COMPONENT_FILE);
        final Set<Object> keys = componentProp.keySet();

        keys.stream()
                .map(key -> (String) key)
                .forEach(key -> load(componentProp.getProperty(key)));
    }

    private void loadConfig(final String packageName) {
        final List<Class<?>> classes = PackageScanner.scan(packageName);

        classes.stream()
                .filter(clazz -> clazz.isAnnotationPresent(Configuration.class))
                .forEach(clazz -> {
                    final Method[] methods = clazz.getDeclaredMethods();
                    initJavaConfigBeans(clazz, methods);
                });
    }

    private void initJavaConfigBeans(final Class<?> clazz, final Method[] methods) {
        stream(methods)
                .filter(method -> method.isAnnotationPresent(Bean.class))
                .forEach(method -> {
                    final Bean annotation = method.getAnnotation(Bean.class);
                    String beanName = annotation.value();
                    if (beanName.equals(EMPTY_STRING)) {
                        final String simpleName = method.getReturnType().getSimpleName();
                        beanName = uncapitalize(simpleName);
                    }
                    final Object config = newInstance(clazz);
                    container.put(beanName, invoke(method, config));
                });
    }

    private void load(final String packageName) {
        final List<Class<?>> classes = PackageScanner.scan(packageName);

        classes.stream()
                .filter(clazz -> clazz.isAnnotationPresent(Component.class))
                .forEach(this::initAnnotationBean);
    }

    private void initAnnotationBean(final Class<?> clazz) {
        final Component annotation = clazz.getAnnotation(Component.class);
        String beanName = annotation.value();
        if (beanName.equals(EMPTY_STRING)) {
            final String simpleName = clazz.getSimpleName();
            beanName = uncapitalize(simpleName);
        }
        container.put(beanName, newInstance(clazz));
    }

    private void injectFields() {
        container.values().forEach(this::injectFields);
    }

    private void injectFields(final Object bean) {
        for (Class clazz = bean.getClass(); clazz != Object.class; clazz = clazz.getSuperclass()) {
            final Field[] fields = clazz.getDeclaredFields();
            stream(fields)
                    .filter(field -> field.isAnnotationPresent(Inject.class))
                    .forEach(field -> {
                        final Object injectedValue = getBean(field.getName());
                        setField(field, bean, injectedValue);
                    });
        }
    }

    public Object getBean(final String name) {
        return container.get(name);
    }

    public <T> T getBean(final String name, final Class<T> clazz) {
        return clazz.cast(container.get(name));
    }
}
