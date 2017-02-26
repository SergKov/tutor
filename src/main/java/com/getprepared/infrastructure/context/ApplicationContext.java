package com.getprepared.infrastructure.context;

import com.getprepared.annotation.Bean;
import com.getprepared.annotation.Inject;
import com.getprepared.infrastructure.BeanFactory;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by koval on 25.02.2017.
 */
public class ApplicationContext implements BeanFactory {

    private final Map<String, Object> map = new HashMap<>();

    public ApplicationContext() { }

    private void load(final String packageName) throws IOException, IllegalAccessException, InstantiationException {
        final Reflections reflections = new Reflections(packageName, new SubTypesScanner(false));
        final Collection<Class<?>> classes = reflections.getSubTypesOf(Object.class);

        for (Class clazz : classes) {
            if (clazz.isAnnotationPresent(Bean.class)) {
                map.put(clazz.getName(), clazz.newInstance());
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
