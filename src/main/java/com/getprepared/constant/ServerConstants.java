package com.getprepared.constant;

/**
 * Created by koval on 06.01.2017.
 */
public class ServerConstants {

    private ServerConstants() { }

    public static class DAOS {

        private DAOS() { }

        public static final String ANSWER_DAO = "answerDao";

        public static final String QUESTION_DAO = "questionDao";

        public static final String QUIZ_DAO = "quizDao";

        public static final String RESULT_DAO = "resultDao";

        public static final String USER_DAO = "userDao";

        public static final String QUESTION_HISTORY_DAO = "questionHistoryDao";

        public static final String CHOSEN_ANSWER_DAO = "chosenAnswerDao";
    }

    public static class SERVICES {

        private SERVICES() { }

        public static final String ANSWER_SERVICE = "answerService";

        public static final String QUESTION_SERVICE = "questionService";

        public static final String QUIZ_SERVICE = "quizService";

        public static final String RESULT_SERVICE = "resultService";

        public static final String USER_SERVICE = "userService";

        public static final String WORKER_SERVICE = "workerService";

        public static final String QUESTION_HISTORY_SERVICE = "questionHistoryService";

        public static final String CHOSEN_ANSWER_SERVICE = "chosenAnswerService";
    }
}
