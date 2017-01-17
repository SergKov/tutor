package com.getprepared.controller;

import com.getprepared.utils.impl.Messages;

/**
 * Created by koval on 15.01.2017.
 */
public abstract class AbstractController implements Controller {

    public abstract void init();

    protected Messages getMessages() {
        return Messages.getInstance();
    }
}
