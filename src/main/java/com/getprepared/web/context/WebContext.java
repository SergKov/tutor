package com.getprepared.web.context;

import com.getprepared.annotation.Component;
import com.getprepared.annotation.Inject;
import com.getprepared.core.util.PackageScanner;
import com.getprepared.core.util.PropertyUtils;
import com.getprepared.web.annotation.Controller;
import com.getprepared.web.annotation.RequestMapping;
import com.getprepared.web.command.Command;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.*;

import static com.getprepared.context.Registry.getApplicationContext;
import static com.getprepared.core.util.ReflectionUtils.newInstance;
import static com.getprepared.core.util.ReflectionUtils.setField;
import static com.getprepared.web.constant.PropertyConstant.CONTROLLER_FILE;
import static java.util.Arrays.stream;

/**
 * Created by koval on 23.03.2017.
 */
@Component
public class WebContext {

    @Inject
    private PropertyUtils propertyUtils;

    @Inject
    private PackageScanner packageScanner;

    private final Map<String, Command> container = new HashMap<>();

    public WebContext() {
        initController();
        injectFields();
    }

    private void initController() {
        final Properties prop = propertyUtils.getProperty(CONTROLLER_FILE);
        final Set<Object> keys = prop.keySet();

        keys.stream()
                .map(key -> (String) key)
                .forEach(key -> load(prop.getProperty(key)));
    }

    @SuppressWarnings("unchecked")
    private void load(final String packageName) {
        final List<Class<?>> classes = packageScanner.scan(packageName);

        classes.stream()
                .filter(clazz -> clazz.isAnnotationPresent(Controller.class))
                .map(clazz -> (Class<Command>) clazz)
                .forEach(this::initAnnotationBean);
    }

    private void initAnnotationBean(final Class<Command> clazz) {
        if (clazz.isAnnotationPresent(RequestMapping.class)) {
            final Annotation[] annotations = clazz.getAnnotations();
            stream(annotations).forEach(annotation -> putToContainer(clazz));
        }
    }

    private void putToContainer(final Class<Command> clazz) {
        final RequestMapping requestMapping = (RequestMapping) clazz.getAnnotation(RequestMapping.class);
        final String beanName = requestMapping.value();
        container.put(beanName, newInstance(clazz));
    }

    private void injectFields() {
        container.values().forEach(this::injectFields);
    }

    private void injectFields(final Object bean) {
        for (Class clazz = bean.getClass(); clazz != Object.class; clazz = clazz.getSuperclass()) {
            final Field[] fields = clazz.getDeclaredFields();
            stream(fields)
                    .filter(field -> field.isAnnotationPresent(Inject.class))
                    .forEach(field -> {
                        final Object injectedValue = getApplicationContext().getBean(field.getName());
                        setField(field, bean, injectedValue);
                    });
        }
    }

    public Command getCommand(final String commandName) {
        return container.get(commandName);
    }
}
