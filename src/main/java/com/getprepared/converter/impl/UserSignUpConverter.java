package com.getprepared.converter.impl;

import com.getprepared.annotation.Component;
import com.getprepared.converter.Converter;
import com.getprepared.domain.User;

/**
 * Created by koval on 09.03.2017.
 */
@Component("userSignUpConverter")
public class UserSignUpConverter<F, E> implements Converter<F, E> {

    @Override
    public E convert(final F form) {
        return (E) new User();
    }
}
