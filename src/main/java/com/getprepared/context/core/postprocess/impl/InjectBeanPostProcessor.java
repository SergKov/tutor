package com.getprepared.context.core.postprocess.impl;

import com.getprepared.annotation.Inject;
import com.getprepared.annotation.PostProcessor;
import com.getprepared.context.BeanFactory;
import com.getprepared.context.core.postprocess.BeanPostProcessor;

import java.lang.reflect.Field;

import static com.getprepared.core.util.ReflectionUtils.setField;
import static java.util.Arrays.stream;

/**
 * Created by koval on 29.03.2017.
 */
@PostProcessor
public class InjectBeanPostProcessor implements BeanPostProcessor {

    @Override
    public void process(final Object bean, final BeanFactory beanFactory) {
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
        return 0;
    }
}