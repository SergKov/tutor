package com.getprepared.utils;

import com.getprepared.utils.impl.ValidationImpl;

import java.util.HashMap;
import java.util.Map;

import static com.getprepared.constant.UtilsConstant.FORM_VALIDATION;

/**
 * Created by koval on 14.01.2017.
 */
public class UtilsFactory {

    private static final UtilsFactory instance = new UtilsFactory();

    public static UtilsFactory getInstance() {
        return instance;
    }

    private Map<String, Object> mapping;

    private UtilsFactory() { }

    public void init() {
        mapping = new HashMap<>();
        mapping.put(FORM_VALIDATION, new ValidationImpl());
    }

    public <T> T getUtil(final String key, final Class<T> clazz) {
        return clazz.cast(mapping.get(key));
    }
}
