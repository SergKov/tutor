package com.getprepared.web.controller.common;

import com.getprepared.annotation.Inject;
import com.getprepared.core.util.Messages;
import com.getprepared.web.controller.Controller;

import javax.servlet.http.HttpServletRequest;

import static com.getprepared.web.constant.PageConstants.NAMES;
import static com.getprepared.web.constant.ValidationConstant.REGEX;
import static com.getprepared.web.constant.WebConstants.REQUEST_ATTRIBUTES.*;

/**
 * Created by koval on 15.01.2017.
 */
public abstract class AbstractSignInController implements Controller {

    @Inject
    private Messages messages;

    protected void fillPage(final HttpServletRequest request) {
        request.setAttribute(TITLE, messages.getMessage(NAMES.SIGN_IN, request.getLocale()));
        request.setAttribute(EMAIL_REGEX, REGEX.EMAIL);
        request.setAttribute(PASSWORD_REGEX, REGEX.PASSWORD);
    }
}
