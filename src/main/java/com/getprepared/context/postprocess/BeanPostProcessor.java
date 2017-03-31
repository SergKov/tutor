package com.getprepared.context.postprocess;

import com.getprepared.context.ApplicationContext;

/**
 * Created by koval on 29.03.2017.
 */
public interface BeanPostProcessor extends Ordered {

    void process(Object bean, ApplicationContext applicationContext);
}
