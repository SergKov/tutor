package com.getprepared.context.postprocess.impl;

import com.getprepared.annotation.Inject;
import com.getprepared.context.annotation.PostProcessor;
import com.getprepared.context.ApplicationContext;
import com.getprepared.context.postprocess.BeanPostProcessor;
import com.getprepared.core.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Optional;

import static com.getprepared.context.constant.ServerConstant.REFLECTION_UTILS;
import static java.util.Arrays.stream;

/**
 * Created by koval on 29.03.2017.
 */
@PostProcessor
public class InjectBeanPostProcessor implements BeanPostProcessor {

    @Override
    public void postProcessBeforeInitialization(final String beanName, final Object bean,
                                                final ApplicationContext applicationContext) {

        for (Class clazz = bean.getClass(); clazz != Object.class; clazz = clazz.getSuperclass()) {
            final Field[] fields = clazz.getDeclaredFields();
            stream(fields)
                    .filter(field -> field.isAnnotationPresent(Inject.class))
                    .forEach(field -> {
                        final Object injectedValue = applicationContext.getBean(field.getName());
                        applicationContext.getBean(REFLECTION_UTILS, ReflectionUtils.class)
                                .setField(field, bean, injectedValue);
                    });
        }
    }

    @Override
    public Optional<Object> postProcessAfterInitialization(final String beanName, final Object bean) {
        return Optional.empty();
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
