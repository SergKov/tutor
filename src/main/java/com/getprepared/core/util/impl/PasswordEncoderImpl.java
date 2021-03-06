package com.getprepared.core.util.impl;

import com.getprepared.annotation.Component;
import com.getprepared.core.util.PasswordEncoder;
import org.mindrot.jbcrypt.BCrypt;

/**
 * Created by koval on 14.01.2017.
 */
@Component("passwordEncoder")
public final class PasswordEncoderImpl implements PasswordEncoder {

    @Override
    public String encode(final String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    @Override
    public boolean matches(final String rawPassword, final String encodedPassword) {
        return BCrypt.checkpw(rawPassword, encodedPassword);
    }
}
