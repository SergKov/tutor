package com.getprepared.utils;

import com.getprepared.exception.ParseException;

/**
 * Created by koval on 19.01.2017.
 */
public interface Parser {

    Long parseLong(String value) throws ParseException;
}
