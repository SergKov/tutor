package com.getprepared.utils.impl;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by koval on 15.01.2017.
 */
public class Messages {
    private static final String BUNDLE = "resources.messages.base";
    private static Messages messages = new Messages();
    private ResourceBundle resoureBoundle;

    private Messages() { }

    public static Messages getInstance() {
        return messages;
    }

    public String getMessage(String key, Locale locale) {
        resoureBoundle = ResourceBundle.getBundle(BUNDLE, locale);
        return resoureBoundle.getString(key);
    }
}
