package com.getprepared.web.tag;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by koval on 13.04.2017.
 */
public final class DateTimeUtils {

    private DateTimeUtils() { }

    public static String formatLocalDateTime(final LocalDateTime dateTime, final String pattern) {
        return dateTime.format(DateTimeFormatter.ofPattern(pattern));
    }
}
