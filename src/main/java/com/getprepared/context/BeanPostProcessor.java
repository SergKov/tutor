package com.getprepared.context;

/**
 * Created by koval on 29.03.2017.
 */
public interface BeanPostProcessor extends Ordered {

    void postProcess(Object bean, BeanFactory beanFactory);
}
