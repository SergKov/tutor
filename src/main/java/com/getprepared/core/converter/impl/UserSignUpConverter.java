package com.getprepared.core.converter.impl;

import com.getprepared.annotation.Component;
import com.getprepared.core.converter.Converter;
import com.getprepared.persistence.domain.Role;
import com.getprepared.persistence.domain.User;
import com.getprepared.web.form.UserSignUpForm;

/**
 * Created by koval on 09.03.2017.
 */
@Component
public class UserSignUpConverter implements Converter<UserSignUpForm, User> {

    @Override
    public User convert(final UserSignUpForm form) {
        final User user = new User();
        user.setRole(Role.valueOf(form.getRole()));
        user.setName(form.getName());
        user.setSurname(form.getSurname());
        user.setEmail(form.getEmail());
        user.setPassword(form.getPassword());
        return user;
    }
}
