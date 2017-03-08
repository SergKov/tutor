package com.getprepared.util.impl;

import com.getprepared.annotation.Component;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by koval on 15.01.2017.
 */
@Component("messages")
public class Messages {

    private static final String BUNDLE = "/messages/base";

    private ResourceBundle resoureBoundle;

    public Messages() { }

    public String getMessage(final String key, final Locale locale) {
        resoureBoundle = ResourceBundle.getBundle(BUNDLE, locale);
        return resoureBoundle.getString(key);
    }
}