package com.getprepared.context.core.postprocess.impl;

import com.getprepared.context.BeanFactory;
import com.getprepared.context.core.postprocess.BeanPostProcessor;

import java.lang.reflect.Method;

/**
 * Created by koval on 30.03.2017.
 */
public class TransactionalBeanPostProcessor implements BeanPostProcessor { // TODO

    @Override
    public void process(Object bean, BeanFactory beanFactory) {
        final Method[] methods = bean.getClass().getDeclaredMethods();
    }

    @Override
    public int getOrder() {
        return STEP;
    }
}
