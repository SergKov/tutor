package com.getprepared.infrastructure.context;

import com.getprepared.annotation.Bean;
import com.getprepared.annotation.Inject;
import com.getprepared.infrastructure.BeanFactory;
import org.apache.log4j.Logger;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by koval on 25.02.2017.
 */
public class ApplicationContext implements BeanFactory {

    private static final Logger LOG = Logger.getLogger(ApplicationContext.class);

    private static final String PACKAGE = "com.getprepared";

    private final Map<String, Object> map = new HashMap<>();

    public ApplicationContext() {
        load(PACKAGE);
        injectFields();
    }

    private void load(final String packageName)  {
        final Reflections reflections = new Reflections(packageName, new SubTypesScanner(false));
        final Collection<Class<?>> classes = reflections.getSubTypesOf(Object.class);

        for (Class clazz : classes) {
            if (clazz.isAnnotationPresent(Bean.class)) {
                final Bean annotation = (Bean) clazz.getAnnotation(Bean.class);
                try {
                    map.put(annotation.value(), clazz.newInstance());
                } catch (InstantiationException | IllegalAccessException e) {
                    final String errorMsg = String.format("Failed to instantiate class %s", clazz.getName());
                    LOG.error(errorMsg, e);
                    throw new IllegalStateException(errorMsg, e);
                }
            }
        }
    }

    private void injectFields() {
        for (Object bean : map.values()) {
            final Field[] fields = bean.getClass().getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(Inject.class)) {
                    field.setAccessible(true);
                    final Object injectedValue = getBean(field.getName());
                    try {
                        field.set(bean, injectedValue);
                    } catch (IllegalArgumentException | IllegalAccessException e) {
                        final String errorMsg = String.format("Failed to initialize field %s", field.getName());
                        LOG.error(errorMsg, e);
                        throw new IllegalStateException(errorMsg, e);
                    }
                }
            }
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
