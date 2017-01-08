package com.getprepared.utils;

import java.sql.ResultSet;

import static com.getprepared.utils.SqlCallable.call;

/**
 * Created by koval on 08.01.2017.
 */
public class ResultSetUtils {

    private ResultSetUtils() { }

    public static boolean next(final ResultSet rs) {
        return call(rs::next, "Failed to get next");
    }
}
