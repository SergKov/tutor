package com.getprepared.infrastructure.context;

import com.getprepared.annotation.Bean;
import com.getprepared.annotation.Inject;
import com.getprepared.infrastructure.BeanFactory;
import com.getprepared.util.impl.ReflectionUtils;
import org.apache.log4j.Logger;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.lang.reflect.Field;
import java.util.Arrays;
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

    public ApplicationContext(String jndi) {
        load(PACKAGE);
        initDataSource(jndi);
        injectFields();
    }

    private void load(final String packageName) {
        final Reflections reflections = new Reflections(packageName, new SubTypesScanner(false));
        final Collection<Class<?>> classes = reflections.getSubTypesOf(Object.class);

        classes.forEach(clazz -> {
            if (clazz.isAnnotationPresent(Bean.class)) {
                final Bean annotation = (Bean) clazz.getAnnotation(Bean.class);
                final String beanName = annotation.value();
                map.put(annotation.value(), ReflectionUtils.newInstance(clazz));
            }
        });
    }

    private void injectFields() {
        map.values().forEach(this::injectFields);
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
        return map.get(name);
    }

    @Override
    public <T> T getBean(final String name, final Class<T> clazz) {
        final Object bean = map.get(name);
        return clazz.cast(bean);
    }

    private void initDataSource(final String jndi) {
        try {
            map.put("dataSource", new InitialContext().lookup(jndi));
        } catch (final NamingException e) {
            LOG.fatal("Failed to get DataSource", e);
            throw new IllegalStateException(e);
        }
    }
}
