package com.getprepared.utils.impl;

import com.getprepared.utils.PasswordEncoder;
import org.mindrot.jbcrypt.BCrypt;

import java.util.Base64;

/**
 * Created by koval on 14.01.2017.
 */
public class PasswordEncoderImpl implements PasswordEncoder {

    @Override
    public String encode(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
}
