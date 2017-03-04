package com.getprepared.infrastructure.context;

import com.getprepared.annotation.Bean;
import com.getprepared.annotation.Inject;
import com.getprepared.infrastructure.BeanFactory;
import com.getprepared.util.impl.PackageScanner;
import com.getprepared.util.impl.ReflectionUtils;
import org.apache.log4j.Logger;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by koval on 25.02.2017.
 */
public class ApplicationContext implements BeanFactory {

    private static final Logger LOG = Logger.getLogger(ApplicationContext.class);

    private static final String PREFIX = "com.getprepared.";
    private static final String CONTROLLER_PACKAGE = PREFIX + "controller";
    private static final String DAO_PACKAGE = PREFIX + "dao.impl";
    private static final String DATABASE_PACKAGE = PREFIX + "database";
    private static final String SERVICE_PACKAGE = PREFIX + "service";
    private static final String UTIL_PACKAGE = PREFIX + "util";

    private final Map<String, Object> map = new HashMap<>();

    public ApplicationContext(String jndi) {
        initDataSource(jndi);

        load(CONTROLLER_PACKAGE);
        load(DAO_PACKAGE);
        load(DATABASE_PACKAGE);
        load(SERVICE_PACKAGE);
        load(UTIL_PACKAGE);

        injectFields();
    }

    private void load(final String packageName) {
        final List<Class<?>> classes = PackageScanner.scan(packageName);

        classes.forEach(clazz -> {
            if (clazz.isAnnotationPresent(Bean.class)) {
                final Bean annotation = (Bean) clazz.getAnnotation(Bean.class);
                final String beanName = annotation.value();
                map.put(annotation.value(), ReflectionUtils.newInstance(clazz));
            }
        });
    }

    private void initDataSource(final String jndi) {
        try {
            map.put("dataSource", new InitialContext().lookup(jndi));
        } catch (final NamingException e) {
            LOG.fatal("Failed to get DataSource", e);
            throw new IllegalStateException(e);
        }
    }

    private void injectFields() {
        map.values().forEach(this::injectFields);
    }

    private void injectFields(final Object bean) {
        for (Class clazz = bean.getClass(); clazz != Object.class; clazz = clazz.getSuperclass()) {
            final Field[] fields = clazz.getDeclaredFields();
            Arrays.stream(fields).forEach(field -> {
                if (field.isAnnotationPresent(Inject.class)) {
                    final Object injectedValue = getBean(field.getName());
                    ReflectionUtils.setField(field, bean, injectedValue);
                }
            });
        }
    }

    @Override
    public Object getBean(final String name) {
        return map.get(name);
    }

    @Override
    public <T> T getBean(final String name, final Class<T> clazz) {
        final Object bean = map.get(name);
        return clazz.cast(bean);
    }
}
