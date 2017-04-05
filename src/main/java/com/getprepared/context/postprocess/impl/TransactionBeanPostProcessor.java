package com.getprepared.context.postprocess.impl;


import com.getprepared.annotation.Service;
import com.getprepared.annotation.Transactional;
import com.getprepared.context.ApplicationContext;
import com.getprepared.context.postprocess.BeanPostProcessor;
import com.getprepared.persistence.database.TransactionManager;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static com.getprepared.context.Registry.getApplicationContext;
import static com.getprepared.context.constant.ServerConstant.TRANSACTION_MANAGER;

/**
 * Created by koval on 02.04.2017.
 */
public class TransactionBeanPostProcessor implements BeanPostProcessor {

    private final Map<String, Class> map = new HashMap<>();

    @Override
    public void postProcessBeforeInitialization(final String beanName, final Object bean
            , final ApplicationContext applicationContext) {
        final Class<?> beanClass = bean.getClass();
        if (beanClass.isAnnotationPresent(Service.class)) {
            map.put(beanName, beanClass);
        }
    }

    @Override
    public Optional<Object> postProcessAfterInitialization(final String beanName, final Object bean) {

        final Class beanClass = map.get(beanName);

        final TransactionManager transactionManager
                = getApplicationContext().getBean(TRANSACTION_MANAGER, TransactionManager.class);

        if (beanClass != null) {
            return Optional.of(Proxy.newProxyInstance(bean.getClass().getClassLoader(), bean.getClass().getInterfaces(),
                                                                                            new InvocationHandler() {
                @Override
                public Object invoke(final Object proxy, final Method method, final Object[] args) throws Throwable {

                    if (!method.isAnnotationPresent(Transactional.class)) {
                        return method.invoke(bean, args);
                    }

                    Object result = null;
                    try {
                        transactionManager.begin();
                        result = method.invoke(bean, args);
                        transactionManager.commit();
                    } catch (final InvocationTargetException e) {
                        transactionManager.rollback();
                        throw e.getCause();
                    }
                    return result;
                }
            }));
        }
        return Optional.empty();
    }

    @Override
    public int getOrder() {
        return 1_000;
    }
}
