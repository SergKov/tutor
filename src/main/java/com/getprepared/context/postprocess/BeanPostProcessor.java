package com.getprepared.context.postprocess;

import com.getprepared.context.ApplicationContext;

import java.util.Optional;

/**
 * Created by koval on 29.03.2017.
 */
public interface BeanPostProcessor extends Ordered {

    void postProcessBeforeInitialization(String beanName, Object bean, final ApplicationContext applicationContext);

    Optional<Object> postProcessAfterInitialization(String beanName, Object bean);
}
