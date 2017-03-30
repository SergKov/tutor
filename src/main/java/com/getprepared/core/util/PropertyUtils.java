package com.getprepared.core.util;

import org.apache.log4j.Logger;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static com.getprepared.core.constant.PropertyConstant.*;

/**
 * Created by koval on 06.01.2017.
 */
public final class PropertyUtils {

    private static final Logger LOG = Logger.getLogger(PropertyUtils.class);

    private PropertyUtils() { }

    private static Map<String, Properties> cache = new HashMap<>();

    static { // TODO
        initProperty(FILES_NAME.USER);
        initProperty(FILES_NAME.RESULT);
        initProperty(FILES_NAME.QUESTION);
        initProperty(FILES_NAME.ANSWER);
        initProperty(FILES_NAME.QUIZ);
        initProperty(FILES_NAME.COMPONENT_FILE);
        initProperty(FILES_NAME.CONFIGURATION_FILE);
        initProperty(FILES_NAME.CONTROLLER_FILE);
        initProperty(FILES_NAME.POST_PROCESS_FILE);
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
