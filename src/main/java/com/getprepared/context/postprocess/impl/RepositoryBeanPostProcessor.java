package com.getprepared.context.postprocess.impl;

import com.getprepared.context.ApplicationContext;
import com.getprepared.context.postprocess.BeanPostProcessor;
import com.getprepared.core.util.ConnectionUtils;
import com.getprepared.persistence.annotation.Repository;
import com.getprepared.persistence.database.ConnectionProvider;
import org.apache.log4j.Logger;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static com.getprepared.context.Registry.getApplicationContext;
import static com.getprepared.context.constant.ServerConstant.CONNECTION_PROVIDER;
import static com.getprepared.context.constant.ServerConstant.CONNECTION_UTILS;

/**
 * Created by koval on 06.04.2017.
 */
public class RepositoryBeanPostProcessor implements BeanPostProcessor {

    private final Map<String, Class> map = new HashMap<>();

    @Override
    public void postProcessBeforeInitialization(final String beanName, final Object bean,
                                                final ApplicationContext applicationContext) {
        final Class<?> beanClass = bean.getClass();
        if (beanClass.isAnnotationPresent(Repository.class)) {
            map.put(beanName, beanClass);
        }
    }

    @Override
    public Optional<Object> postProcessAfterInitialization(final String beanName, final Object bean) {

        final Class beanClass = map.get(beanName);

        final ConnectionProvider connectionProvider = getApplicationContext().getBean(CONNECTION_PROVIDER,
                ConnectionProvider.class);

        final ConnectionUtils connectionUtils = getApplicationContext().getBean(CONNECTION_UTILS,
                ConnectionUtils.class);

        if (beanClass != null) {
            return Optional.of(Proxy.newProxyInstance(beanClass.getClassLoader(), beanClass.getInterfaces(),
                    new InvocationHandler() {
                @Override
                public Object invoke(final Object proxy, final Method method, final Object[] args) throws Throwable {
                    try {
                        return method.invoke(bean, args);
                    } catch (final InvocationTargetException e) {
                        throw e.getCause();
                    } finally {
                        if (!connectionProvider.isTransactional()) {
                            connectionUtils.close(connectionProvider.getConnection());
                        }
                    }
                }
            }));
        }
        return Optional.empty();
    }

    @Override
    public int getOrder() {
        return 2_000;
    }
}
