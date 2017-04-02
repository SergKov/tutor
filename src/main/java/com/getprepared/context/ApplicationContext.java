package com.getprepared.context;

import com.getprepared.annotation.*;
import com.getprepared.context.postprocess.BeanPostProcessor;
import com.getprepared.context.postprocess.Ordered;
import com.getprepared.core.util.PackageScanner;
import com.getprepared.core.util.PropertyUtils;

import java.lang.reflect.Method;
import java.util.*;

import static com.getprepared.core.constant.PropertyConstant.FILES_NAME.*;
import static com.getprepared.core.constant.ServerConstant.EMPTY_STRING;
import static com.getprepared.core.util.ReflectionUtils.invoke;
import static com.getprepared.core.util.ReflectionUtils.newInstance;
import static java.util.Arrays.stream;
import static org.apache.commons.lang3.StringUtils.uncapitalize;

/**
 * Created by koval on 25.02.2017.
 */
public class ApplicationContext {

    private static final String APPLICATION_CONTEXT = "applicationContext";

    private final Map<String, Object> container = new HashMap<>();
    private final List<BeanPostProcessor> beanPostProcessors = new ArrayList<>();

    ApplicationContext() { }

    void init() {
        container.put(APPLICATION_CONTEXT, this);
        initConfig();
        initComponent();
        initPostProcessors();
        doPostProcess();
    }

    private void initConfig() {
        final Properties configurationProp = PropertyUtils.getProperty(CONFIGURATION_FILE);

        configurationProp.keySet().stream()
                .map(key -> (String) key)
                .forEach(key -> loadConfig(configurationProp.getProperty(key)));
    }

    private void loadConfig(final String packageName) {
        final List<Class<?>> classes = PackageScanner.scan(packageName);

        classes.stream()
                .filter(clazz -> clazz.isAnnotationPresent(Configuration.class))
                .forEach(clazz -> {
                    final Method[] methods = clazz.getDeclaredMethods();
                    initJavaConfigBeans(clazz, methods);
                });
    }

    private void initJavaConfigBeans(final Class<?> clazz, final Method[] methods) {
        stream(methods)
                .filter(method -> method.isAnnotationPresent(Bean.class))
                .forEach(method -> {
                    final Bean annotation = method.getAnnotation(Bean.class);
                    String beanName = annotation.value();
                    beanName = manageBeanName(method.getReturnType(), beanName);
                    final Object config = newInstance(clazz);
                    container.put(beanName, invoke(method, config));
                });
    }

    private void initComponent() {
        final Properties componentProp = PropertyUtils.getProperty(COMPONENT_FILE);

        componentProp.keySet().stream()
                .map(key -> (String) key)
                .forEach(key -> load(componentProp.getProperty(key)));
    }

    private void load(final String packageName) {
        final List<Class<?>> classes = PackageScanner.scan(packageName);

        classes.stream()
                .filter(clazz -> clazz.isAnnotationPresent(Component.class) || clazz.isAnnotationPresent(Service.class))
                .forEach(this::initAnnotationBean);
    }

    private void initAnnotationBean(final Class<?> clazz) {
        String beanName = null;
        if (clazz.getAnnotation(Component.class) != null) {
            final Component annotation = clazz.getAnnotation(Component.class);
            beanName = annotation.value();
        } else if (clazz.getAnnotation(Service.class) != null) {
            final Service annotation = clazz.getAnnotation(Service.class);
            beanName = annotation.value();
        }

        container.put(manageBeanName(clazz, beanName),
                        newInstance(clazz));
    }

    private String manageBeanName(final Class<?> clazz, String beanName) {
        if (beanName.equals(EMPTY_STRING)) {
            final String simpleName = clazz.getSimpleName();
            beanName = uncapitalize(simpleName);
        }
        return beanName;
    }

    private void initPostProcessors() {
        final Properties postProcessorProperties = PropertyUtils.getProperty(POST_PROCESS_FILE);

        postProcessorProperties.keySet().stream()
                .map(key -> (String) key)
                .forEach(key -> loadPostProcessor(postProcessorProperties.getProperty(key)));
    }

    private void loadPostProcessor(final String packageName) {
        final List<Class<?>> classes = PackageScanner.scan(packageName);

        classes.stream()
                .filter(clazz -> clazz.isAnnotationPresent(PostProcessor.class))
                .forEach(clazz -> beanPostProcessors.add((BeanPostProcessor) newInstance(clazz)));

        beanPostProcessors.sort(Comparator.comparingInt(Ordered::getOrder));
    }

    private void doPostProcess() {
        beanPostProcessors.forEach(this::doPostProcess);
    }

    private void doPostProcess(final BeanPostProcessor beanPostProcessor) {
        container.values().forEach(bean -> beanPostProcessor.process(bean, this));
    }

    public Object getBean(final String name) {
        return container.get(name);
    }

    public <T> T getBean(final String name, final Class<T> clazz) {
        return clazz.cast(container.get(name));
    }
}
