package com.getprepared.context.core.postprocess;

import com.getprepared.context.BeanFactory;

/**
 * Created by koval on 29.03.2017.
 */
public interface BeanPostProcessor extends Ordered {

    void process(Object bean, BeanFactory beanFactory);
}
