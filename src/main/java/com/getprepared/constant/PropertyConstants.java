package com.getprepared.constant;

/**
 * Created by koval on 06.01.2017.
 */
public class PropertyConstants {

    private PropertyConstants() { }

    public static class FILES_NAMES {

        private FILES_NAMES() { }

        private static final String PREFIX_DS = "/ds/";

        private static final String PREFIX_SQL = "/sql/";

        private static final String SUFFIX = ".properties";

        public static final String DS_TEST_SETTING = PREFIX_DS + "mysql" + SUFFIX; // TODO rename

        public static final String DS_SETTING = PREFIX_DS + "setting" + SUFFIX; // TODO rename

        public static final String ANSWER = PREFIX_SQL + "answer" + SUFFIX;

        public static final String QUESTION = PREFIX_SQL + "question" + SUFFIX;

        public static final String QUIZ = PREFIX_SQL + "quiz" + SUFFIX;

        public static final String SPECIALITY = PREFIX_SQL + "speciality" + SUFFIX;

        public static final String USER = PREFIX_SQL + "user" + SUFFIX;

        public static final String RESULT = PREFIX_SQL + "result" + SUFFIX;
    }

    public static class KEYS {

        private KEYS() { }

        public static final String DS = "datasource"; //TODO rename

        public static final String URL = "url";

        public static final String USER = "user";

        public static final String PASSWORD = "password";

        public static final String SAVE = "save";

        public static final String INSTITUTE = "institute";

        public static final String FIND_BY_ID = "findById";

        public static final String FIND_BY_CREDENTIALS = "findByCredentials";

        public static final String FIND_BY_EMAIL = "findByEmail";

        public static final String FIND_BY_QUIZ_ID = "findByQuizId";

        public static final String FIND_BY_USER_ID = "findByUserId";

        public static final String FIND_BY_SPECIALITY_ID = "findBySpecialityId";

        public static final String FIND_BY_QUESTION_ID = "findByQuestionId";

        public static final String FIND_ALL = "findAll";

        public static final String UPDATE_TIME = "updateTime";

        public static final String UPDATE_CREDENTIALS = "updateCredentials";

        public static final String REMOVE = "remove";

        public static final String REMOVE_BY_ID = "removeById";

        public static final String REMOVE_BY_QUESTION_ID = "removeByQuestionId";
    }
}
