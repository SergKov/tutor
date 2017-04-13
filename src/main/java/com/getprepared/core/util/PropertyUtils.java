package com.getprepared.core.util;

import org.apache.log4j.Logger;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static com.getprepared.context.constant.PropertyConstant.FILES_NAME.*;
import static com.getprepared.persistence.constant.PropertyConstant.FILES_NAME.*;
import static com.getprepared.web.constant.PropertyConstant.FILES_NAME.CONTROLLER_FILE;

/**
 * Created by koval on 06.01.2017.
 */
public final class PropertyUtils {

    private static final Logger LOG = Logger.getLogger(PropertyUtils.class);

    public PropertyUtils() { init(); }

    private Map<String, Properties> cache = new HashMap<>();

    private void init() {
        initProperty(USER);
        initProperty(RESULT);
        initProperty(QUESTION);
        initProperty(ANSWER);
        initProperty(QUIZ);
        initProperty(COMPONENT_FILE);
        initProperty(CONFIGURATION_FILE);
        initProperty(CONTROLLER_FILE);
        initProperty(POST_PROCESS_FILE);
    }

    public Properties getProperty(final String fileName) {
        return cache.get(fileName);
    }

    private void initProperty(final String fileName) {
        final Properties prop = new Properties();
        try {
            final InputStream is = PropertyUtils.class.getResourceAsStream(fileName);
            prop.load(is);
            cache.put(fileName, prop);
        } catch (final Exception e) {
            LOG.warn(String.format("Failed to load file %s", fileName), e);
            throw new IllegalStateException(e);
        }
    }
}
