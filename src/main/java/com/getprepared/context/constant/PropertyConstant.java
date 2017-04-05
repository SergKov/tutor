package com.getprepared.context.constant;

/**
 * Created by koval on 05.04.2017.
 */
public final class PropertyConstant {

    private PropertyConstant() { }

    public static final class FILES_NAME {

        private FILES_NAME() { }

        private static final String PREFIX_SERVER = "/packages/";

        private static final String SUFFIX = ".properties";

        public static final String CONFIGURATION_FILE = PREFIX_SERVER + "configuration" + SUFFIX;

        public static final String COMPONENT_FILE = PREFIX_SERVER + "component" + SUFFIX;

        public static final String POST_PROCESS_FILE = PREFIX_SERVER + "postprocess" + SUFFIX;
    }
}
