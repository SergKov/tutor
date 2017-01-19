package com.getprepared.utils.impl;

import com.getprepared.exception.ParseException;
import com.getprepared.utils.Parser;
import org.apache.log4j.Logger;

/**
 * Created by koval on 19.01.2017.
 */
public class ParserImpl implements Parser {

    private static final Logger LOG = Logger.getLogger(ParserImpl.class);

    @Override
    public Long parseLong(final String value) throws ParseException {
        try {
            return Long.parseLong(value);
        } catch (final NumberFormatException e) {
            final String msg = String.format("Failed to parse %s", value);
            LOG.warn(msg, e);
            throw new ParseException(msg, e);
        }
    }
}
