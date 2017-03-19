package com.getprepared.converter;

/**
 * Created by koval on 09.03.2017.
 */
public interface Converter<F, E> {

    E convert(F form);
}
