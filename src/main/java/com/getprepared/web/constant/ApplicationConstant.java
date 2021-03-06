package com.getprepared.web.constant;

/**
 * Created by koval on 14.01.2017.
 */
public final class ApplicationConstant {

    private ApplicationConstant() { }

    public static final String REDIRECT = "redirect";

    public static final long DEFAULT_PAGE_NUMBER = 1L;

    public static final long DEFAULT_NUMBER_OF_ELEMENTS = 5L;

    public static final class PATH {

        private PATH() { }

        private static final String PREFIX = "/WEB-INF/pages/";

        private static final String STUDENT_PREFIX = "/WEB-INF/pages/student/";

        private static final String TUTOR_PREFIX = "/WEB-INF/pages/tutor/";

        private static final String SUFFIX = ".jsp";

        public static final String SIGN_UP = PREFIX + "signUp" + SUFFIX;

        public static final String STUDENT_SIGN_IN = STUDENT_PREFIX + "signIn" + SUFFIX;

        public static final String STUDENT_UPDATE_PASSWORD = STUDENT_PREFIX + "updatePassword" + SUFFIX;

        public static final String STUDENT_HOME_PAGE = STUDENT_PREFIX + "homePage" + SUFFIX;

        public static final String STUDENT_TEST = STUDENT_PREFIX + "test" + SUFFIX;

        public static final String STUDENT_RESULT = STUDENT_PREFIX + "result" + SUFFIX;

        public static final String STUDENT_RESULTS = STUDENT_PREFIX + "results" + SUFFIX;

        public static final String TUTOR_SIGN_IN = TUTOR_PREFIX + "signIn" + SUFFIX;

        public static final String TUTOR_UPDATE_PASSWORD = TUTOR_PREFIX + "updatePassword" + SUFFIX;

        public static final String TUTOR_QUIZZES = TUTOR_PREFIX + "quizzes" + SUFFIX;

        public static final String TUTOR_QUIZ_ADD = TUTOR_PREFIX + "quizAdd" + SUFFIX;

        public static final String TUTOR_QUESTIONS = TUTOR_PREFIX + "questions" + SUFFIX;

        public static final String TUTOR_QUESTION = TUTOR_PREFIX + "question" + SUFFIX;

        public static final String TUTOR_QUESTION_ADD = TUTOR_PREFIX + "questionAdd" + SUFFIX;
    }

    public static final class LINK {

        private LINK() { }

        public static final String STUDENT_SIGN_IN = "/";

        public static final String LOCALE = "/locale";

        public static final String SIGN_UP = "/sign-up";

        public static final String STUDENT_HOME_PAGE = "/student";

        public static final String STUDENT_UPDATE_PASSWORD = "/student/update-password";

        public static final String STUDENT_TEST = "/student/test";

        public static final String STUDENT_RESULTS = "/student/results";

        public static final String STUDENT_RESULT = "/student/test/result";

        public static final String TUTOR_SIGN_IN = "/tutor";

        public static final String TUTOR_UPDATE_PASSWORD = "/tutor/update-password";

        public static final String TUTOR_QUIZZES = "/tutor/quizzes";

        public static final String TUTOR_QUIZ_ADD = "/tutor/quizzes/add";

        public static final String TUTOR_QUESTIONS = "/tutor/quizzes/questions";

        public static final String TUTOR_QUESTION_ADD = "/tutor/quizzes/questions/add";

        public static final String SIGN_OUT = "/sign-out";
    }

    public static final class COMMAND {

        private COMMAND() { }

        public static final String STUDENT_SIGN_IN = "studentSignIn";

        public static final String STUDENT_UPDATE_PASSWORD = "studentUpdatePassword";

        public static final String STUDENT_TEST_START = "testStart";

        public static final String STUDENT_ANSWER_SAVE = "answerSave";

        public static final String STUDENT_TEST_END = "testEnd";

        public static final String TUTOR_SIGN_IN = "tutorSignIn";

        public static final String TUTOR_UPDATE_PASSWORD = "tutorUpdatePassword";

        public static final String TUTOR_QUIZ_ADD = "quizAdd";

        public static final String TUTOR_QUIZ_UPDATE = "quizUpdate";

        public static final String TUTOR_QUIZ_ACTIVE = "quizActive";

        public static final String TUTOR_QUIZ_REMOVE = "quizRemove";

        public static final String TUTOR_QUESTION_REMOVE = "questionRemove";

        public static final String TUTOR_QUESTION_ADD = "questionAdd";

        public static final String SIGN_UP = "signUp";

        public static final String LANGUAGE = "language";
    }
}