package com.getprepared.web.context;

import com.getprepared.annotation.Inject;
import com.getprepared.core.util.PropertyUtils;
import com.getprepared.web.annotation.Controller;

import java.lang.reflect.Field;
import java.util.*;

import static com.getprepared.core.util.PackageScanner.scan;
import static com.getprepared.core.util.ReflectionUtils.newInstance;
import static com.getprepared.core.util.ReflectionUtils.setField;
import static java.util.Arrays.stream;
import static org.apache.commons.lang3.StringUtils.uncapitalize;

/**
 * Created by koval on 23.03.2017.
 */
public class WebContext {

    private static final String EMPTY_STRING = "";

    private final Properties prop = PropertyUtils.initProp("/server/web.properties");

    private final Map<String, Object> container = new HashMap<>();

    public WebContext() {
        initController();
    }

    private void initController() {
        final Set<Object> keys = prop.keySet();

        keys.stream()
                .map(key -> (String) key)
                .forEach(key -> load(prop.getProperty(key)));
    }

    private void load(final String packageName) {
        final List<Class<?>> classes = scan(packageName);

        classes.stream()
                .filter(clazz -> clazz.isAnnotationPresent(Controller.class))
                .forEach(this::initAnnotationBean);
    }

    private void initAnnotationBean(final Class<?> clazz) {
        final Controller annotation = (Controller) clazz.getAnnotation(Controller.class);
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
        final Object bean = container.get(name);
        return clazz.cast(bean);
    }
}
