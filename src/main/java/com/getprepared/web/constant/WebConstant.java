package com.getprepared.web.constant;

/**
 * Created by koval on 16.01.2017.
 */
public final class WebConstant {

    private WebConstant() { }

    public static final class INPUT {

        private INPUT() { }

        public static final String ROLE = "role";

        public static final String EMAIL = "email";

        public static final String PASSWORD = "password";

        public static final String OLD_PASSWORD = "oldPassword";

        public static final String NEW_PASSWORD = "newPassword";

        public static final String NAME = "name";

        public static final String SURNAME = "surname";

        public static final String QUESTION_ID = "question-id";

        public static final String QUIZ_ID = "quiz-id";

        public static final String QUIZ_NAME = "quiz-name";

        public static final String QUESTION_TEXT = "questionText";

        public static final String QUESTION_NUMBER = "question-number";

        public static final String ANSWER_TEXT = "answerText[]";

        public static final String ANSWER_TYPE = "answerType[]";

        public static final String CHOSEN_ANSWER_ID = "chosenAnswersId[]";

        public static final String SHOW_ELEMENTS = "show-elements";

        public static final String CURRENT_PAGE = "current-page";
    }

    public static final class REQUEST_ATTRIBUTE {

        private REQUEST_ATTRIBUTE() { }

        public static final String TITLE = "title";

        public static final String USER = "user";

        public static final String EMAIL = "email";

        public static final String ROLES = "roles";

        public static final String QUIZZES = "quizzes";

        public static final String ERROR_MSG = "errorMsg";

        public static final String ERROR_MSGS = "errorMsgs";

        public static final String REPEAT_PWD_MSG = "repeatPassword";

        public static final String EMAIL_REGEX = "emailRegex";

        public static final String PASSWORD_REGEX = "passwordRegex";

        public static final String QUIZ_NAME_REGEX = "quizRegex";

        public static final String NAME_REGEX = "nameRegex";

        public static final String SURNAME_REGEX = "surnameRegex";

        public static final String QUIZ = "quiz";

        public static final String QUIZ_FORM = "quizForm";

        public static final String QUESTION = "question";

        public static final String TEST_QUESTION = "testQuestion";

        public static final String QUESTIONS = "questions";

        public static final String PAGINATION = "pagination";

        public static final String CURRENT_QUESTION = "currentQuestion";

        public static final String ANSWER_TYPES = "answerTypes";

        public static final String RESULTS = "results";

        public static final String LANGUAGE = "language";
    }

    public static final class SESSION_ATTRIBUTE {

        private SESSION_ATTRIBUTE() { }

        public static final String STUDENT = "student";

        public static final String TUTOR = "tutor";

        public static final String QUIZ_ID = "quiz-id";

        public static final String TEST = "test";

        public static final String LANGUAGE = "language";

        public static final String MARK = "mark";

        public static final String CURRENT_PAGE = "currentPage";

        public static final String SHOW_ELEMENTS = "showElements";
    }
}
