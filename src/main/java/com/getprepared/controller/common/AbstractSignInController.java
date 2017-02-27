package com.getprepared.controller.common;

import com.getprepared.annotation.Inject;
import com.getprepared.controller.AbstractController;
import com.getprepared.util.impl.Messages;

import javax.servlet.http.HttpServletRequest;

import static com.getprepared.constant.PageConstants.NAMES;
import static com.getprepared.constant.UtilsConstant.REGEX;
import static com.getprepared.constant.WebConstants.REQUEST_ATTRIBUTES.*;

/**
 * Created by koval on 15.01.2017.
 */
public abstract class AbstractSignInController extends AbstractController {

    @Inject
    private Messages messages;

    protected void fillPage(final HttpServletRequest request) {
        request.setAttribute(TITLE, messages.getMessage(NAMES.SIGN_IN, request.getLocale()));
        request.setAttribute(EMAIL_REGEX, REGEX.EMAIL);
        request.setAttribute(PASSWORD_REGEX, REGEX.PASSWORD);
    }
}
