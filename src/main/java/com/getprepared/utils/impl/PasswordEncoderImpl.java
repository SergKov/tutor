package com.getprepared.utils.impl;

import com.getprepared.utils.PasswordEncoder;

import java.util.Base64;

/**
 * Created by koval on 14.01.2017.
 */
public class PasswordEncoderImpl implements PasswordEncoder {

    @Override
    public String encode(final String password) {
        return Base64.getEncoder().encodeToString(password.getBytes());
    }
}
