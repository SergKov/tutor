package com.getprepared.utils.impl;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by koval on 15.01.2017.
 */
public class Messages {

    private static final String BUNDLE = "/messages/base";

    private static final Messages instance = new Messages();

    public static Messages getInstance() {
        return instance;
    }

    private ResourceBundle resoureBoundle;

    private Messages() { }

    public String getMessage(final String key, final Locale locale) {
        resoureBoundle = ResourceBundle.getBundle(BUNDLE, locale);
        return resoureBoundle.getString(key);
    }
}
