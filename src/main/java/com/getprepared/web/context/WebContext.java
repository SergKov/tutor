package com.getprepared.web.context;

import com.getprepared.core.util.PropertyUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * Created by koval on 23.03.2017.
 */
public class WebContext {

    private static final Properties prop = PropertyUtils.initProp("/server/web.properties");

    private final Map<String, Object> container = new HashMap<>();

    public WebContext() {

    }

    private void load() {
        final Set<Object> keys = prop.keySet();

        keys.stream()
                .map(key -> (String) key);

    }
}
