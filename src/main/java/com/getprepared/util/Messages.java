package com.getprepared.util;

import com.getprepared.annotation.Component;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import static java.util.Collections.emptyList;
import static java.util.ResourceBundle.getBundle;
import static org.apache.commons.collections4.CollectionUtils.isEmpty;

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

    public List<String> getMessages(final List<String> keys, final Locale locale) {
        if (isEmpty(keys)) {
            return emptyList();
        }
        final List<String> messages = new ArrayList<>();
        keys.forEach(key -> {
            final String message = getMessage(key, locale);
            if (StringUtils.isEmpty(message)) {
                messages.add(message);
            }
        });
        return messages;
    }
}