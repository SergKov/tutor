package com.getprepared.context.postprocess;

import com.getprepared.context.BeanFactory;
import com.getprepared.context.BeanPostProcessor;

import java.lang.reflect.Method;

/**
 * Created by koval on 30.03.2017.
 */
public class TransactionalBeanPostProcessor implements BeanPostProcessor {

    @Override
    public void process(Object bean, BeanFactory beanFactory) {
        final Method[] methods = bean.getClass().getDeclaredMethods();
    }

    @Override
    public int getOrder() {
        return STEP;
    }
}
