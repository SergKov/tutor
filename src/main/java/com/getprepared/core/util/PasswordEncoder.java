package com.getprepared.core.util;

/**
 * Created by koval on 14.01.2017.
 */
public interface PasswordEncoder {

    String encode(String password);

    boolean matches(String rawPassword, String encodedPassword);
}