package com.getprepared.context;

/**
 * Created by koval on 25.02.2017.
 */
public interface BeanFactory {

    Object getBean(String name);

    <T> T getBean(String name, Class<T> clazz);
}
