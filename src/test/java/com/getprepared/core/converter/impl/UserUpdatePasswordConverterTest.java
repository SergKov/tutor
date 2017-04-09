package com.getprepared.core.converter.impl;

import com.getprepared.core.converter.Converter;
import com.getprepared.persistence.domain.User;
import com.getprepared.web.form.UserUpdatePasswordForm;
import org.junit.Assert;
import org.junit.Test;

import static com.getprepared.constant.ServerConstant.PASSWORD;
import static org.junit.Assert.*;

/**
 * Created by koval on 06.04.2017.
 */
public class UserUpdatePasswordConverterTest {

    private final Converter<UserUpdatePasswordForm, User> converter
            = new UserUpdatePasswordConverter();

    @Test
    public void requireConvert() throws Exception {
        final UserUpdatePasswordForm userUpdatePasswordForm = new UserUpdatePasswordForm();
        userUpdatePasswordForm.setNewPassword(PASSWORD);
        final User user = converter.convert(userUpdatePasswordForm);
        Assert.assertEquals(PASSWORD, user.getPassword());
    }
}