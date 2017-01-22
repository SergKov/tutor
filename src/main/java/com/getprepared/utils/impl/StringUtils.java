package com.getprepared.utils.impl;

/**
 * Created by koval on 22.01.2017.
 */
public class StringUtils {

    private StringUtils() { }

    public static boolean isNumericSpace(final CharSequence cs) {

        if (cs == null) {
            return false;
        } else {
            final int sz = cs.length();

            for (int i = 0; i < sz; i++) {
                if (!Character.isDigit(cs.charAt(i)) && cs.charAt(i) != 32) {
                    return false;
                }
            }

            return true;
        }
    }
}
