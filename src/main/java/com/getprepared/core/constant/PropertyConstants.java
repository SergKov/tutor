package com.getprepared.core.constant;

/**
 * Created by koval on 06.01.2017.
 */
public final class PropertyConstants {

    private PropertyConstants() { }

    public static final class FILES_NAMES {

        private FILES_NAMES() { }

        private static final String PREFIX_DS = "/ds/";

        private static final String PREFIX_SQL = "/sql/";

        private static final String PREFIX_SERVER = "/server/";

        private static final String SUFFIX = ".properties";

        public static final String CONFIGURATION_FILE = PREFIX_SERVER + "configuration" + SUFFIX;

        public static final String COMPONENT_FILE = PREFIX_SERVER + "component" + SUFFIX;

        public static final String CONTROLLER_FILE = PREFIX_SERVER + "controller" + SUFFIX;

        public static final String DS_TEST_SETTING = PREFIX_DS + "testDataSource" + SUFFIX;

        public static final String ANSWER = PREFIX_SQL + "answer" + SUFFIX;

        public static final String QUESTION = PREFIX_SQL + "question" + SUFFIX;

        public static final String QUIZ = PREFIX_SQL + "quiz" + SUFFIX;

        public static final String USER = PREFIX_SQL + "user" + SUFFIX;

        public static final String RESULT = PREFIX_SQL + "result" + SUFFIX;
    }

    public static final class KEYS {

        private KEYS() { }

        public static final String URL = "url";

        public static final String USER = "user";

        public static final String PASSWORD = "password";

        public static final String SAVE = "save";

        public static final String FIND_BY_ID = "findById";

        public static final String FIND_BY_STUDENT_EMAIL = "findByStudentEmail";

        public static final String FIND_BY_TUTOR_EMAIL = "findByTutorEmail";

        public static final String FIND_BY_USER_ID = "findByUserId";

        public static final String FIND_ALL = "findAll";

        public static final String REMOVE = "remove";

        public static final String REMOVE_BY_ID = "removeById";

        public static final String COUNT = "count";
    }
}
