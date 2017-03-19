package com.getprepared.converter.impl;

import com.getprepared.annotation.Component;
import com.getprepared.converter.Converter;
import com.getprepared.converter.form.UserSignUpForm;
import com.getprepared.domain.Role;
import com.getprepared.domain.User;

/**
 * Created by koval on 09.03.2017.
 */
@Component("userSignUpConverter")
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
