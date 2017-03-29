package com.getprepared.context;

/**
 * Created by koval on 24.03.2017.
 */
public final class Registry {

    private static final ApplicationContext applicationContext;
    private static final WebContext webContext;

    static {
        applicationContext = new ApplicationContext();
        applicationContext.init();

        webContext = new WebContext();
        webContext.init();
    }

    private Registry() { }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static WebContext getWebContext() {
        return webContext;
    }
}
