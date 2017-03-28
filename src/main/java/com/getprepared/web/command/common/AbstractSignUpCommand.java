package com.getprepared.web.command.common;

import com.getprepared.annotation.Inject;
import com.getprepared.core.util.Messages;
import com.getprepared.persistence.domain.Role;
import com.getprepared.web.command.Command;

import javax.servlet.http.HttpServletRequest;

import static com.getprepared.web.constant.PageConstant.ERROR;
import static com.getprepared.web.constant.PageConstant.TITLE.SIGN_UP;
import static com.getprepared.web.constant.ValidationConstant.REGEX;
import static com.getprepared.web.constant.WebConstant.REQUEST_ATTRIBUTE.*;

/**
 * Created by koval on 17.01.2017.
 */
public abstract class AbstractSignUpCommand implements Command {

    @Inject
    private Messages messages;

    protected void fillPage(final HttpServletRequest request) {
        request.setAttribute(TITLE, messages.getMessage(SIGN_UP, request.getLocale()));
        request.setAttribute(ROLES, Role.values());
        request.setAttribute(NAME_REGEX, REGEX.NAME);
        request.setAttribute(SURNAME_REGEX, REGEX.SURNAME);
        request.setAttribute(EMAIL_REGEX, REGEX.EMAIL);
        request.setAttribute(PASSWORD_REGEX, REGEX.PASSWORD);
        request.setAttribute(REPEAT_PWD_MSG, messages.getMessage(ERROR.PASSWORDS_NOT_MATCH, request.getLocale()));
    }
}
