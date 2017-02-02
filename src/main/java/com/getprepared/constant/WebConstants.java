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

        public static final String QUESTION_ID = "questionId";

        public static final String QUIZ_ID = "quizId";

        public static final String QUIZ_NAME = "quizName";

        public static final String QUESTION_TEXT = "questionText";

        public static final String ANSWER_TEXT = "answerText[]";

        public static final String ANSWER_TYPE = "answerType[]";
    }

    public static class REQUEST_ATTRIBUTES {

        private REQUEST_ATTRIBUTES() { }

        public static final String TITLE = "title";

        public static final String USER = "user";

        public static final String EMAIL = "email";

        public static final String ROLES = "roles";

        public static final String QUIZ_NAME = "quizName";

        public static final String QUIZZES = "quizzes";

        public static final String ERROR_MSG = "errorMsg";

        public static final String REPEAT_PWD_MSG = "repeatPassword";

        public static final String CONFIRM_MSG = "confirmMsg";

        public static final String EMAIL_REGEX = "emailRegex";

        public static final String PASSWORD_REGEX = "passwordRegex";

        public static final String QUIZ_NAME_REGEX = "quizRegex";

        public static final String NAME_REGEX = "nameRegex";

        public static final String SURNAME_REGEX = "surnameRegex";

        public static final String QUIZ = "quiz";

        public static final String QUESTION = "question";

        public static final String QUESTIONS = "questions";

        public static final String ANSWER_TYPE_REGEX = "answerTypeRegex";

        public static final String ADD_QUESTION = "addQuestion";

        public static final String ANSWER_TYPES = "answerTypes";

        public static final String QUESTIONS_LENGTH = "questionsLength";

        public static final String QUESTION_NUMBER = "questionNumber";

        public static final String ANSWERS = "answers";

        public static final String REPEAT_PASSWORD = "repeatPassword";

        public static final String PAGE_INDEX = "page"; //TODO

        public static final String PAGE_SIZE = "size"; //TODO
    }

    public static class SESSION_ATTRIBUTES {

        private SESSION_ATTRIBUTES() { }

        public static final String STUDENT = "student";

        public static final String TUTOR = "tutor";

        public static final String QUIZ = "quiz";
    }

    public static class FILTERS_VARIABLES {

        private FILTERS_VARIABLES() { }

        public static final String ROLE = "role";

        public static final String HOME_PAGE = "homePage";

        public static final String ENCODING = "UTF-8";
    }
}
