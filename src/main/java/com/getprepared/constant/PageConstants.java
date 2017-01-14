package com.getprepared.constant;

/**
 * Created by koval on 14.01.2017.
 */
public class PageConstants {

    private PageConstants() { }

    public static final String REDIRECT = "redirect";

    public static class PAGES {

        private PAGES() { }

        public static final String PREFIX = "/WEB-INF/pages/";

        public static final String SUFFIX = ".jsp";

        public static final String HOME = PREFIX + "homePage" + SUFFIX;

        String NOT_FOUND = PREFIX + "pageNotFound" + SUFFIX;
    }

    public static class LINKS {

        private LINKS() { }

        public static final String HOME_PAGE = "/";

        public static final String NOT_FOUND = "/pageNotFound";
    }
}
