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

        public static final String TUTOR_SPECIALITIES = PREFIX + "tutorSpecialities" + SUFFIX;
    }

    public static class LINKS {

        private LINKS() { }

        public static final String STUDENT_SIGN_IN = "/";

        public static final String SIGN_UP = "/signUp";

        public static final String STUDENT_HOME_PAGE = "/studentHomePage";

        public static final String SPECIALITIES = "/specialities";

        public static final String QUIZZES = "/specialities/quizzes";

        public static final String ADD_SPECIALITY = "/tutor/specialities";

        public static final String ADD_QUIZ = "/tutor/specialities/quizzes";

        public static final String TEST = "/test";

        public static final String NOT_FOUND = "/pageNotFound";

        public static final String TUTOR_SIGN_IN = "/tutor";
    }

    public static class NAMES {

        private NAMES() { }

        public static final String PAGE_NOT_FOUND = "PageNotFound";

        public static final String SIGN_IN = "SignIn";

        public static final String SIGN_UP = "SignUp";

        public static final String HOME_PAGE = "HomePage";

        public static final String SPECIALITIES = "Specialities";
    }

    public static class FORMS {

        private FORMS() { }

        public static final String STUDENT_SIGN_IN = "studentSignIn";

        public static final String SIGN_UP = "signUp";

        public static final String STUDENT_HOME_PAGE = "studentHomePage";

        public static final String TUTOR_SIGN_IN = "tutorSignIn";
    }

    public static class ERRORS {

        private ERRORS() { }

        public static final String SYSTEM_NOT_AVAILABLE = "System is not available";

        public static final String CREDENTIALS_INVALIDATED = "You have entered invalidated credentials";

        public static final String STUDENT_IS_NOT_EXISTS = "User with such credentials is not exists";

        public static final String TUTOR_IS_NOT_EXISTS = "Tutor with such credentials is not exists";

        public static final String DATA_INVALIDATED = "You have entered invalidated data";

        public static final String STUDENT_EXISTS = "Such student already exists";

        public static final String SPECIALITY_ID_INCORRECT = "Speciality id is incorrect";

        public static final String SPECIALITY_IS_NOT_FOUND = "Speciality is not found";

        public static final String SPECIALITY_EXISTS = "Such speciality already exists";

        public static final String STUDENT_INVALIDATED = "Student invalidated";

        public static final String QUIZ_ID_INCORRECT = "Quiz id is incorrect";
    }
}