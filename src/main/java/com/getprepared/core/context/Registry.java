package com.getprepared.core.context;

import com.getprepared.web.context.WebContext;

/**
 * Created by koval on 24.03.2017.
 */
public final class Registry {

    private static final String WEB_CONTEXT = "webContext";
    private static final ApplicationContext applicationContext;

    static {
        applicationContext = new ApplicationContext();
        applicationContext.init();
    }

    private Registry() { }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static WebContext getWebContext() {
        return applicationContext.getBean(WEB_CONTEXT, WebContext.class);
    }
}
