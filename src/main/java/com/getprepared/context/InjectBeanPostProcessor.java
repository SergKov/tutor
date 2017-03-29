package com.getprepared.context;

import com.getprepared.annotation.Inject;

import java.lang.reflect.Field;

import static com.getprepared.core.util.ReflectionUtils.setField;
import static java.util.Arrays.stream;

/**
 * Created by koval on 29.03.2017.
 */
public class InjectBeanPostProcessor implements BeanPostProcessor {

    public static final int FIRST = 1;

    @Override
    public void postProcess(final Object bean, final BeanFactory beanFactory) {
        for (Class clazz = bean.getClass(); clazz != Object.class; clazz = clazz.getSuperclass()) {
            final Field[] fields = clazz.getDeclaredFields();
            stream(fields)
                    .filter(field -> field.isAnnotationPresent(Inject.class))
                    .forEach(field -> {
                        final Object injectedValue = beanFactory.getBean(field.getName());
                        setField(field, bean, injectedValue);
                    });
        }
    }

    @Override
    public int getOrder() {
        return FIRST;
    }
}
