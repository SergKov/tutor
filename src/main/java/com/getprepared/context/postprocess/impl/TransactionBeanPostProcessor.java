package com.getprepared.context.postprocess.impl;

import com.getprepared.context.ApplicationContext;
import com.getprepared.context.postprocess.BeanPostProcessor;

/**
 * Created by koval on 02.04.2017.
 */
public class TransactionBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object process(final Object bean, final ApplicationContext applicationContext) {

//        if (bean.getClass().isAnnotationPresent(Service.class)) {
//            final TransactionManager transactionManager =
//                    getApplicationContext().getBean("transactionalManager", TransactionManager.class);
//
//            return Proxy.newProxyInstance(null, bean.getClass().getInterfaces(), new InvocationHandler() {
//
//                @Override
//                public Object invoke(final Object proxy, final Method method, final Object[] args) throws Throwable {
//
//                    if (!method.isAnnotationPresent(Transactional.class)) {
//                        return method.invoke(bean, args);
//                    }
//
//                    Object result = null;
//                    try {
//                        transactionManager.begin();
//                        result = method.invoke(bean, args);
//                        transactionManager.commit();
//                    } catch (final InvocationTargetException e) {
//                        transactionManager.rollback();
//                        throw e.getCause();
//                    }
//                    return result;
//                }
//            });
//        }

        return bean;

    }

    @Override
    public int getOrder() {
        return 1_000;
    }
}
