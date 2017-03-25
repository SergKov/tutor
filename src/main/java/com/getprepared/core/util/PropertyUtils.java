package com.getprepared.core.util;

import org.apache.log4j.Logger;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by koval on 06.01.2017.
 */
public class PropertyUtils {

    private static final Logger LOG = Logger.getLogger(PropertyUtils.class);

    private PropertyUtils() { }

    private static Map<String, Properties> cache = new HashMap<>();

    public static Properties getProperty(final String fileName) {
        return cache.get(fileName) == null ? initProperties(fileName) : cache.get(fileName);
    }

    private static Properties initProperties(final String fileName) {
        final Properties prop = new Properties();
        try {
            final InputStream is = PropertyUtils.class.getResourceAsStream(fileName);
            prop.load(is);
            return prop;
        } catch (final Exception e) {
            LOG.warn(String.format("Failed to load file %s", fileName), e);
            throw new IllegalStateException(e);
        }
    }
}
