package com.getprepared.core.converter.impl;

import com.getprepared.core.converter.Converter;
import com.getprepared.persistence.domain.Role;
import com.getprepared.persistence.domain.User;
import com.getprepared.web.form.UserSignUpForm;
import org.junit.Test;

import static com.getprepared.constant.ServerConstant.*;
import static org.junit.Assert.assertEquals;

/**
 * Created by koval on 08.04.2017.
 */
public class UserSignUpConverterTest {

    private final Converter<UserSignUpForm, User> converter = new UserSignUpConverter();

    @Test
    public void requireConvert() throws Exception {
        final UserSignUpForm form = new UserSignUpForm();
        form.setRole(Role.STUDENT.name());
        form.setName(NAME);
        form.setSurname(NAME);
        form.setEmail(EMAIL);
        form.setPassword(PASSWORD);
        final User user = converter.convert(form);
        assertEquals(Role.valueOf(form.getRole()), user.getRole());
        assertEquals(form.getName(), user.getName());
        assertEquals(form.getSurname(), user.getSurname());
        assertEquals(form.getEmail(), user.getEmail());
        assertEquals(form.getPassword(), user.getPassword());
    }
}
