package com.getprepared.constant;

/**
 * Created by koval on 14.01.2017.
 */
public class PageConstants {

    private PageConstants() { }

    public static final String REDIRECT = "redirect";

    public static class PAGES {

        private PAGES() { }

        private static final String PREFIX = "/WEB-INF/pages/";

        private static final String SUFFIX = ".jsp";

        public static final String STUDENT_SIGN_IN = PREFIX + "studentSignIn" + SUFFIX;

        public static final String NOT_FOUND = PREFIX + "pageNotFound" + SUFFIX;

        public static final String SIGN_UP = PREFIX + "signUp" + SUFFIX;

        public static final String STUDENT_HOME_PAGE = PREFIX + "studentHomePage" + SUFFIX;

        public static final String TUTOR_SIGN_IN = PREFIX + "tutorSignIn" + SUFFIX;

        public static final String TUTOR_QUIZZES = PREFIX + "tutorQuizzes" + SUFFIX;

        public static final String ADD_QUIZ = PREFIX + "addQuiz" + SUFFIX;

        public static final String QUESTIONS = PREFIX + "questionsPage" + SUFFIX;

        public static final String QUESTION = PREFIX + "question" + SUFFIX;

        public static final String ADD_QUESTION = PREFIX + "addQuestion" + SUFFIX;

        public static final String TEST = PREFIX + "test" + SUFFIX;
    }

    public static class LINKS {

        private LINKS() { }

        public static final String STUDENT_SIGN_IN = "/";

        public static final String SIGN_UP = "/signUp";

        public static final String STUDENT_HOME_PAGE = "/studentHomePage";

        public static final String TUTOR_QUIZZES = "/tutor/quizzes";

        public static final String ADD_QUIZ = "/tutor/quizzes/addQuiz";

        public static final String QUESTIONS = "/tutor/quizzes/questions";

        public static final String ADD_QUESTION = "/tutor/quizzes/questions/addQuestion";

        public static final String TEST = "/test";

        public static final String NOT_FOUND = "/pageNotFound";

        public static final String TUTOR_SIGN_IN = "/tutor";

        public static final String SIGN_OUT = "/signOut";
    }

    public static class NAMES {

        private NAMES() { }

        public static final String PAGE_NOT_FOUND = "PageNotFound";

        public static final String SIGN_IN = "SignIn";

        public static final String SIGN_UP = "SignUp";

        public static final String HOME_PAGE = "HomePage";

        public static final String QUIZZES = "Quizzes";

        public static final String ADD_QUIZ = "AddQuiz";

        public static final String QUESTIONS = "Questions";

        public static final String QUESTION = "Question";

        public static final String ADD_QUESTION = "AddQuestion";

        public static final String TEST = "Test";
    }

    public static class FORMS {

        private FORMS() { }

        public static final String STUDENT_SIGN_IN = "studentSignIn";

        public static final String SIGN_UP = "signUp";

        public static final String STUDENT_HOME_PAGE = "studentHomePage";

        public static final String TUTOR_SIGN_IN = "tutorSignIn";

        public static final String ADD_QUIZ = "addQuiz";

        public static final String REMOVE_QUIZ = "quizRemove";

        public static final String REMOVE_QUESTION = "questionRemove";

        public static final String QUIZ_QUESTIONS = "quizQuestions";

        public static final String QUESTION = "question";

        public static final String ADD_QUESTION = "addQuestion";

        public static final String STUDENT_TEST = "studentTest";
    }

    public static class ERRORS {

        private ERRORS() { }

        public static final String CREDENTIALS_INVALIDATED = "YouHaveEnteredInvalidatedCredentials";

        public static final String DATA_INVALIDATED = "YouHaveEnteredInvalidatedData";

        public static final String USER_EXISTS = "UserExists";

        public static final String USER_NOT_FOUND = "UserNotExists";

        public static final String TUTOR_NOT_FOUND = "TutorNotExists";

        public static final String QUIZ_NOT_FOUND = "QuizIsNotFound";

        public static final String QUESTION_NOT_FOUND = "QuestionIsNotFound";

        public static final String QUESTION_EXISTS = "QuestionExists";

        public static final String STUDENT_INVALIDATED = "StudentInvalidated";

        public static final String INVALIDATED_ID = "InvalidatedId";

        public static final String QUIZ_EXISTS = "SuchQuizAlreadyExists";

        public static final String FILL_NOT_ALL_FIELDS = "FillNotAllFields";

        public static final String INVALIDATED_ANSWERS = "InvalidatedAnswers";
    }
}