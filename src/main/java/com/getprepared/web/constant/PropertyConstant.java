package com.getprepared.web.constant;

/**
 * Created by koval on 05.04.2017.
 */
public final class PropertyConstant {

    private PropertyConstant() { }

    public static final class FILES_NAME {

        private FILES_NAME() { }

        private static final String PREFIX_SERVER = "/packages/";

        private static final String SUFFIX = ".properties";

        public static final String CONTROLLER_FILE = PREFIX_SERVER + "controller" + SUFFIX;
    }

    public static final class KEY {

        private KEY() { }

        public static final String SIGN_IN = "SignIn";

        public static final String SIGN_UP = "SignUp";

        public static final String HOME_PAGE = "HomePage";

        public static final String UPDATE_PASSWORD = "UpdatePassword";

        public static final String QUIZZES = "Quizzes";

        public static final String ADD_QUIZ = "AddQuiz";

        public static final String QUESTIONS = "Questions";

        public static final String QUESTION = "Question";

        public static final String ADD_QUESTION = "AddQuestion";

        public static final String TEST = "Test";

        public static final String RESULT = "Result";

        public static final String PASSWORDS_NOT_MATCH = "PasswordsNotMatch";

        public static final String USER_EXISTS = "UserExists";

        public static final String USER_NOT_FOUND = "UserNotExists";

        public static final String TUTOR_NOT_FOUND = "TutorNotExists";

        public static final String QUESTION_EXISTS = "QuestionExists";

        public static final String QUIZ_EXISTS = "SuchQuizAlreadyExists";

        public static final String FILL_NOT_ALL_FIELDS = "FillNotAllFields";

        public static final String OLD_PASSWORD_INCORRECT = "OldPasswordIncorrect";

        public static final String QUIZ_EMPTY = "QuizEmpty";
    }
}
