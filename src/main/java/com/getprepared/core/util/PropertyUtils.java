package com.getprepared.core.util;

import org.apache.log4j.Logger;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static com.getprepared.core.constant.PropertyConstants.*;

/**
 * Created by koval on 06.01.2017.
 */
public final class PropertyUtils {

    private static final Logger LOG = Logger.getLogger(PropertyUtils.class);

    private PropertyUtils() { }

    private static Map<String, Properties> cache = new HashMap<>();

    static {
        initProperty(FILES_NAMES.USER);
        initProperty(FILES_NAMES.RESULT);
        initProperty(FILES_NAMES.QUESTION);
        initProperty(FILES_NAMES.ANSWER);
        initProperty(FILES_NAMES.QUIZ);
        initProperty(FILES_NAMES.COMPONENT_FILE);
        initProperty(FILES_NAMES.CONFIGURATION_FILE);
        initProperty(FILES_NAMES.CONTROLLER_FILE);
    }

    public static Properties getProperty(final String fileName) {
        return cache.get(fileName);
    }

    private static void initProperty(final String fileName) {
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
