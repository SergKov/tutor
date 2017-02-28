package com.getprepared.controller.common;

import com.getprepared.annotation.Inject;
import com.getprepared.controller.Controller;
import com.getprepared.domain.Role;
import com.getprepared.util.impl.Messages;

import javax.servlet.http.HttpServletRequest;

import static com.getprepared.constant.PageConstants.ERRORS;
import static com.getprepared.constant.PageConstants.NAMES.SIGN_UP;
import static com.getprepared.constant.UtilsConstant.REGEX;
import static com.getprepared.constant.WebConstants.REQUEST_ATTRIBUTES.*;

/**
 * Created by koval on 17.01.2017.
 */
public abstract class AbstractSignUpController implements Controller {

    @Inject
    private Messages messages;

    protected void fillPage(final HttpServletRequest request) {
        request.setAttribute(TITLE, messages.getMessage(SIGN_UP, request.getLocale()));
        request.setAttribute(ROLES, Role.values());
        request.setAttribute(NAME_REGEX, REGEX.NAME);
        request.setAttribute(SURNAME_REGEX, REGEX.SURNAME);
        request.setAttribute(EMAIL_REGEX, REGEX.EMAIL);
        request.setAttribute(PASSWORD_REGEX, REGEX.PASSWORD);
        request.setAttribute(REPEAT_PWD_MSG, messages.getMessage(ERRORS.PASSWORDS_NOT_MATCH, request.getLocale()));
    }
}
