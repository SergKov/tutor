package com.getprepared.web.command.common;

import com.getprepared.annotation.Inject;
import com.getprepared.core.util.Messages;
import com.getprepared.web.command.Command;
import com.getprepared.web.constant.PageConstant;

import javax.servlet.http.HttpServletRequest;

import static com.getprepared.web.constant.ValidationConstant.REGEX;
import static com.getprepared.web.constant.WebConstant.REQUEST_ATTRIBUTE.*;

/**
 * Created by koval on 15.01.2017.
 */
public abstract class AbstractSignInCommand implements Command {

    @Inject
    private Messages messages;

    protected void fillPage(final HttpServletRequest request) {
        request.setAttribute(TITLE, messages.getMessage(PageConstant.TITLE.SIGN_IN, request.getLocale()));
        request.setAttribute(EMAIL_REGEX, REGEX.EMAIL);
        request.setAttribute(PASSWORD_REGEX, REGEX.PASSWORD);
    }
}
