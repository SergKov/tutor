package com.getprepared.utils.factory;

import com.getprepared.utils.impl.PasswordEncoderImpl;
import com.getprepared.utils.impl.ValidationImpl;

import java.util.HashMap;
import java.util.Map;

import static com.getprepared.constant.UtilsConstant.*;

/**
 * Created by koval on 14.01.2017.
 */
public class UtilsFactory {

    private static final UtilsFactory instance = new UtilsFactory();

    public static UtilsFactory getInstance() {
        return instance;
    }

    private Map<String, Object> mapping;

    private void init() {
        mapping = new HashMap<>();
        mapping.put(VALIDATION, new ValidationImpl());
        mapping.put(PASSWORD_ENCODER, new PasswordEncoderImpl());
    }

    private UtilsFactory() {
        init();
    }

    public <T> T getUtil(final String key, final Class<T> clazz) {
        return clazz.cast(mapping.get(key));
    }
}
