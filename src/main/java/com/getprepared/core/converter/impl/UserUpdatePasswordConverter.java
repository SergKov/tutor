package com.getprepared.core.converter.impl;

import com.getprepared.core.converter.Converter;
import com.getprepared.persistence.domain.User;
import com.getprepared.web.form.UserUpdatePasswordForm;

/**
 * Created by koval on 01.04.2017.
 */
public class UserUpdatePasswordConverter implements Converter<UserUpdatePasswordForm, User> {

    @Override
    public User convert(final UserUpdatePasswordForm form) {
        final User user = new User();
        user.setPassword(form.getNewPassword());
        return user;
    }
}
