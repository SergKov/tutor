package com.getprepared.constant;

/**
 * Created by koval on 16.01.2017.
 */
public class WebConstants {

    private WebConstants() { }

    public static class INPUTS {

        private INPUTS() { }

        public static final String ROLE = "role";

        public static final String EMAIL = "email";

        public static final String PASSWORD = "password";

        public static final String NAME = "name";

        public static final String SURNAME = "surname";

        public static final String QUIZ = "quiz";

        public static final String ADD_SPECIALITY = "addSpeciality";
    }

    public static class REQUEST_ATTRIBUTES {

        private REQUEST_ATTRIBUTES() { }

        public static final String TITLE = "title";

        public static final String USER = "user";

        public static final String EMAIL = "email";

        public static final String ROLES = "roles";

        public static final String QUIZ_NAME = "name";

        public static final String QUIZ_LIST = "quizList";

        public static final String ERROR_MSG = "errorMsg";

        public static final String EMAIL_REGEX = "emailRegex";

        public static final String PASSWORD_REGEX = "passwordRegex";

        public static final String QUIZ_NAME_REGEX = "quizRegex";

        public static final String NAME_REGEX = "nameRegex";

        public static final String SURNAME_REGEX = "surnameRegex";

        public static final String SPECIALITIES = "specialities";

        public static final String PAGE_INDEX = "page";

        public static final String PAGE_SIZE = "size";
    }

    public static class SESSION_ATTRIBUTES {

        private SESSION_ATTRIBUTES() { }

        public static final String STUDENT = "student";

        public static final String TUTOR = "tutor";

        public static final String CHOSEN_QUIZ_ID = "chosenQuizId";

        public static final String SPECIALITY_ID = "specialityId";
    }
}
