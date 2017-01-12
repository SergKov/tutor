package com.getprepared.utils;

import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by koval on 06.01.2017.
 */
public class PropertyUtils {

    private static final Logger LOG = Logger.getLogger(PropertyUtils.class);

    private PropertyUtils() { }

    public static Properties initProp(final String fileName) {

        final Properties prop = new Properties();

        try {
            final InputStream is = PropertyUtils.class.getResourceAsStream(fileName);
            prop.load(is);
            return prop;
        } catch (final IOException e) {
            LOG.warn(String.format("Failed to load file %s", fileName), e);
            throw new IllegalStateException(e);
        }

    }
}
