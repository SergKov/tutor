package com.getprepared.web.constant;

/**
 * Created by koval on 14.01.2017.
 */
public final class ValidationConstant {

    private ValidationConstant() { }

    public static final class REGEX {

        private REGEX() { }

        public static final String EMAIL = "^[A-z]\\w+@\\w+\\.[a-z]{2,4}$";

        public static final String PASSWORD = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?!.*\\s).{6,20}$";

        public static final String NAME = "^[A-z]{1}[a-z]{1,19}|[А-я]{1}[а-я]{1,19}$";

        public static final String SURNAME = "^[A-z]{1}[a-z]{1,19}|[А-я]{1}[а-я]{1,19}$";
    }
}
