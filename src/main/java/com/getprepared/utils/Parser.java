package com.getprepared.utils;

import com.getprepared.exception.ParseException;
import jdk.nashorn.internal.runtime.ParserException;

/**
 * Created by koval on 22.01.2017.
 */
public interface Parser {

    Long parseLong(String value) throws ParserException, ParseException;

    Long parseLong(String value, Long defaultValue);
}
