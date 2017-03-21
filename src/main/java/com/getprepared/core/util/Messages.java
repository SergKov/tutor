package com.getprepared.core.util;

import com.getprepared.annotation.Component;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import static java.util.Collections.emptyMap;
import static java.util.ResourceBundle.getBundle;
import static org.apache.commons.collections4.MapUtils.isEmpty;
import static org.apache.commons.lang3.StringUtils.isEmpty;

/**
 * Created by koval on 15.01.2017.
 */
@Component
public class Messages {

    private static final String BUNDLE = "/messages/base";

    private ResourceBundle resoureBoundle;

    public Messages() { }

    public String getMessage(final String key, final Locale locale) {
        resoureBoundle = getBundle(BUNDLE, locale);
        return resoureBoundle.getString(key);
    }

    public Map<String, String> getMessages(final Map<String, String> keys, final Locale locale) {
        if (isEmpty(keys)) {
            return emptyMap();
        }
        final Map<String, String> messages = new HashMap<>();

        keys.forEach((key, value) -> {
            final String message = getMessage(value, locale);
            if (!isEmpty(message)) {
                messages.put(key, message);
            }
        });

        return messages;
    }
}