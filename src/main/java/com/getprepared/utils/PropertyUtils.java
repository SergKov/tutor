package com.getprepared.utils;

import org.apache.log4j.Logger;
import sun.rmi.runtime.Log;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by koval on 06.01.2017.
 */
public class PropertyUtils {

    private static final Logger LOG = Logger.getLogger(PropertyUtils.class);

    private static final PropertyUtils instance = new PropertyUtils();

    public static PropertyUtils getInstance() {
        return instance;
    }

    private PropertyUtils() { }

    public static String getQuery(final String fileName, final String key) {

        final Properties prop = new Properties();

        try {
            prop.load(new FileInputStream(fileName));
            return String.valueOf(prop.get(key));
        } catch (final IOException e) {
            LOG.warn(String.format("Failed to load file %s", fileName), e);
            throw new IllegalStateException(e);
        }
    }
}
