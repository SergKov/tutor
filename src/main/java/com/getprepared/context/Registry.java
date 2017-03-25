package com.getprepared.context;

import com.getprepared.web.context.WebContext;

/**
 * Created by koval on 24.03.2017.
 */
public class Registry {

    private static final ApplicationContext applicationContext = new ApplicationContext();
    private static final WebContext webContext = new WebContext();

    private Registry() { }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static WebContext getWebContext() {
        return webContext;
    }
}
