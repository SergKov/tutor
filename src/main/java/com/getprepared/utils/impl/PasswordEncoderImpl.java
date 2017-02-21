package com.getprepared.utils.impl;

import com.getprepared.utils.PasswordEncoder;
import org.mindrot.jbcrypt.BCrypt;

/**
 * Created by koval on 14.01.2017.
 */
public class PasswordEncoderImpl implements PasswordEncoder {

    @Override
    public String encode(final String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    @Override
    public boolean matches(final String rawPassword, final String encodedPassword) {
        return BCrypt.checkpw(rawPassword, encodedPassword);
    }
}
