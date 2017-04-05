package com.getprepared.context;

import com.getprepared.annotation.*;
import com.getprepared.context.postprocess.BeanPostProcessor;
import com.getprepared.context.postprocess.Ordered;
import com.getprepared.core.util.PackageScanner;
import com.getprepared.core.util.PropertyUtils;
import com.getprepared.core.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.*;

import static com.getprepared.core.constant.PropertyConstant.FILES_NAME.*;
import static com.getprepared.core.constant.ServerConstant.EMPTY_STRING;
import static java.util.Arrays.stream;
import static org.apache.commons.lang3.StringUtils.uncapitalize;

/**
 * Created by koval on 25.02.2017.
 */
public class ApplicationContext {

    static final String APPLICATION_CONTEXT = "applicationContext";
    static final String REFLECTION_UTILS = "reflectionUtils";
    static final String PACKAGE_SCANNER = "packageScanner";
    static final String PROPERTY_UTILS = "propertyUtils";

    private final Map<String, Object> container = new HashMap<>();
    private final List<BeanPostProcessor> beanPostProcessors = new ArrayList<>();

    ApplicationContext() { }

    void init() {
        initDefaults();
        initConfig();
        initComponent();
        initPostProcessors();
        doPostProcess();
    }

    private void initDefaults() {
        container.put(APPLICATION_CONTEXT, this);
        container.put(REFLECTION_UTILS, new ReflectionUtils());
        container.put(PACKAGE_SCANNER, new PackageScanner());
        container.put(PROPERTY_UTILS, new PropertyUtils());
    }

    private void initConfig() {
        final Properties configurationProp = getBean(PROPERTY_UTILS, PropertyUtils.class)
                .getProperty(CONFIGURATION_FILE);

        configurationProp.keySet().stream()
                .map(key -> (String) key)
                .forEach(key -> loadConfig(configurationProp.getProperty(key)));
    }

    private void loadConfig(final String packageName) {
        final List<Class<?>> classes = getBean(PACKAGE_SCANNER, PackageScanner.class).scan(packageName);

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
                    final Object config = getBean(REFLECTION_UTILS, ReflectionUtils.class).newInstance(clazz);
                    container.put(beanName, getBean(REFLECTION_UTILS, ReflectionUtils.class).invoke(method, config));
                });
    }

    private void initComponent() {
        final Properties componentProp = getBean(PROPERTY_UTILS, PropertyUtils.class).getProperty(COMPONENT_FILE);

        componentProp.keySet().stream()
                .map(key -> (String) key)
                .forEach(key -> load(componentProp.getProperty(key)));
    }

    private void load(final String packageName) {
        final List<Class<?>> classes = getBean(PACKAGE_SCANNER, PackageScanner.class).scan(packageName);

        classes.stream()
                .filter(clazz -> clazz.isAnnotationPresent(Component.class) || clazz.isAnnotationPresent(Service.class))
                .forEach(this::initAnnotationBean);
    }

    private void initAnnotationBean(final Class<?> clazz) {
        String beanName = null;
        if (clazz.getAnnotation(Component.class) != null) {
            final Component annotation = clazz.getAnnotation(Component.class);
            beanName = annotation.value();
        } else {
            final Service annotation = clazz.getAnnotation(Service.class);
            beanName = annotation.value();
        }

        container.put(manageBeanName(clazz, beanName),
                getBean(REFLECTION_UTILS, ReflectionUtils.class).newInstance(clazz));
    }

    private String manageBeanName(final Class<?> clazz, String beanName) {
        if (beanName.equals(EMPTY_STRING)) {
            final String simpleName = clazz.getSimpleName();
            beanName = uncapitalize(simpleName);
        }
        return beanName;
    }

    private void initPostProcessors() {
        final Properties postProcessorProperties = getBean(PROPERTY_UTILS, PropertyUtils.class).getProperty(POST_PROCESS_FILE);

        postProcessorProperties.keySet().stream()
                .map(key -> (String) key)
                .forEach(key -> loadPostProcessor(postProcessorProperties.getProperty(key)));
    }

    private void loadPostProcessor(final String packageName) {
        final List<Class<?>> classes = getBean(PACKAGE_SCANNER, PackageScanner.class).scan(packageName);

        classes.stream()
                .filter(clazz -> clazz.isAnnotationPresent(PostProcessor.class))
                .forEach(clazz -> beanPostProcessors.add((BeanPostProcessor) getBean(REFLECTION_UTILS,
                        ReflectionUtils.class).newInstance(clazz)));

        beanPostProcessors.sort(Comparator.comparingInt(Ordered::getOrder));
    }

    private void doPostProcess() {
        beanPostProcessors.forEach(this::doPostProcess);
    }

    private void doPostProcess(final BeanPostProcessor beanPostProcessor) {

        container.forEach((beanName, bean) -> {
            beanPostProcessor.postProcessBeforeInitialization(beanName, bean, this);
        });

        container.forEach((beanName, bean) -> {
            final Optional<Object> proxy = beanPostProcessor.postProcessAfterInitialization(beanName, bean);
            proxy.ifPresent(proxyBean -> container.put(beanName, proxyBean));
        });
    }

    public Object getBean(final String name) {
        return container.get(name);
    }

    public <T> T getBean(final String name, final Class<T> clazz) {
        return clazz.cast(container.get(name));
    }
}
