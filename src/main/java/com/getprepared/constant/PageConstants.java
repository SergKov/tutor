package com.getprepared.constant;

/**
 * Created by koval on 14.01.2017.
 */
public class PageConstants {

    private PageConstants() {
    }

    public static final String REDIRECT = "redirect";

    public static class PAGES {

        private PAGES() { }

        public static final String PREFIX = "/WEB-INF/pages/";

        public static final String SUFFIX = ".jsp";

        public static final String HOME = PREFIX + "home_page" + SUFFIX;

        public static final String NOT_FOUND = PREFIX + "pageNotFound" + SUFFIX;
    }

    public static class LINKS {

        private LINKS() { }

        public static final String HOME_PAGE = "/";

        public static final String CHOOSE_TEST = "/chooseTest";

        public static final String NOT_FOUND = "/pageNotFound";
    }

    public static class NAMES {

        private NAMES() { }

        public static final String PAGE_NOT_FOUND = "PageNotFound";

        public static final String SIGN_IN = "SignIn";

        public static final String SIGN_UP = "SignUp";

        public static final String CHOOSE_TEST = "ChooseTest";
    }

    public static class FORMS {

        private FORMS() { }

        public static final String STUDENT_SIGN_IN = "studentSignIn";
    }

    public static class ERRORS {

        private ERRORS() { }

        public static final String SYSTEM_NOT_AVAILABLE = "System is not available";

        public static final String CREDENTIALS_INVALIDATED = "You have entered invalidated credentials";

        public static final String STUDENT_IS_NOT_EXIST = "Student with such credentials is not exist";
    }
}