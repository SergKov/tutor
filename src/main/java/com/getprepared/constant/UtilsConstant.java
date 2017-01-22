package com.getprepared.constant;

/**
 * Created by koval on 14.01.2017.
 */
public class UtilsConstant {

    private UtilsConstant() { }

    public static final String PASSWORD_ENCODER = "encoder";

    public static final String VALIDATION = "validation";

    public static final String PARSER = "parser";

    public static class REGEX {

        private REGEX() { }

        public static final String EMAIL = "^[a-zA-Z@\\.1-9]{2,32}$";

        public static final String PASSWORD = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?!.*\\s).{6,20}$";

        public static final String NAME = "^[A-z]{1}[a-z]{1,19}|[А-я]{1}[а-я]{1,19}$";

        public static final String SURNAME = "^[A-z]{1}[a-z]{1,19}|[А-я]{1}[а-я]{1,19}$";

        public static final String QUIZ_NAME = "^[\\S]{2,32}$";

        public static final String SPECIALITY_NAME = "^[\\S]{2,32}$";

        public static final String ANSWER_TYPE = "^[CORRECT|INCORRECT]$";
    }
}
