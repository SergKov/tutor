package com.getprepared.web.command.common;

import com.getprepared.annotation.Inject;
import com.getprepared.core.util.Messages;
import com.getprepared.web.command.Command;

import javax.servlet.http.HttpServletRequest;

import static com.getprepared.web.constant.PageConstant.TITLE.SETTINGS;
import static com.getprepared.web.constant.ValidationConstant.REGEX;
import static com.getprepared.web.constant.WebConstant.REQUEST_ATTRIBUTE.*;

/**
 * Created by koval on 31.03.2017.
 */
public abstract class AbstractUpdatePasswordCommand implements Command {

    @Inject
    private Messages messages;

    public void fillPage(final HttpServletRequest request) {
        request.setAttribute(TITLE, messages.getMessage(SETTINGS, request.getLocale()));
        request.setAttribute(EMAIL_REGEX, REGEX.EMAIL);
        request.setAttribute(PASSWORD_REGEX, REGEX.PASSWORD);
    }

}
