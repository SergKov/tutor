package com.getprepared.context.postprocess;

import com.getprepared.context.ApplicationContext;

/**
 * Created by koval on 29.03.2017.
 */
public interface BeanPostProcessor extends Ordered {

    Object process(Object bean, ApplicationContext applicationContext);
}
