package com.getprepared.constant;

/**
 * Created by koval on 06.01.2017.
 */
public class PropertyConstants {

    private PropertyConstants() { }

    public static class FILES_NAMES {

        private FILES_NAMES() { }

        private static final String PREFIX = "/sql/";

        private static final String SUFFIX = ".properties";

        public static final String ANSWER = PREFIX + "answer" + SUFFIX;

        public static final String QUESTION = PREFIX + "question" + SUFFIX;

        public static final String QUIZ = PREFIX + "quiz" + SUFFIX;

        public static final String USER = PREFIX + "user" + SUFFIX;

        public static final String RESULT = PREFIX + "result" + SUFFIX;

        public static final String QUESTION_HISTORY = PREFIX + "question_history" + SUFFIX;

        public static final String CHOSEN_ANSWER = PREFIX + "chosen_answer" + SUFFIX;
    }

    public static class KEYS {

        private KEYS() { }

        public static final String SAVE = "save";

        public static final String CREATE_NEW_QUIZ = "createNewQuiz";

        public static final String FIND_BY_ID = "findById";

        public static final String FIND_BY_CREDENTIALS = "findByCredentials";

        public static final String FIND_BY_EMAIL = "findByEmail";

        public static final String FIND_BY_QUIZ_ID = "findByQuizId";

        public static final String FIND_BY_USER_ID = "findByUserId";

        public static final String FIND_BY_RESULT_ID = "findByResultId";

        public static final String FIND_BY_QUESTION_ID = "findByQuestionId";

        public static final String FIND_ALL = "findAll";

        public static final String UPDATE_TIME = "updateTime";

        public static final String UPDATE_CREDENTIALS = "updateCredentials";

        public static final String REMOVE_BY_ID = "removeById";

        public static final String REMOVE_BY_QUESTION_ID = "removeByQuestionId";
    }
}
