package com.getprepared.constant;

/**
 * Created by koval on 16.01.2017.
 */
public class WebConstants {

    private WebConstants() { }

    public static class INPUTS {

        private INPUTS() { }

        public static final String EMAIL = "email";

        public static final String PASSWORD = "password";
    }

    public static class REQUEST_ATTRIBUTES {

        private REQUEST_ATTRIBUTES() { }

        public static final String TITLE = "title";

        public static final String ERROR_MSG = "errorMsg";

        public static final String EMAIL_REGEX = "emailRegex";

        public static final String PASSWORD_REGEX = "passwordRegex";
    }

    public static class SESSION_ATTRIBUTES {

        private SESSION_ATTRIBUTES() { }

        public static final String STUDENT = "student";
    }
}
