package com.getprepared.controller;

import com.getprepared.util.impl.Messages;

/**
 * Created by koval on 15.01.2017.
 */
public abstract class AbstractController implements Controller {

    public void init() { }

    protected Messages getMessages() {
        return Messages.getInstance();
    }
}
