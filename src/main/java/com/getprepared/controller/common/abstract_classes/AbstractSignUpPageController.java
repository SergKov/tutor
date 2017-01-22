package com.getprepared.controller.common.abstract_classes;

import com.getprepared.controller.AbstractController;
import com.getprepared.domain.Role;

import javax.servlet.http.HttpServletRequest;

import static com.getprepared.constant.PageConstants.NAMES.SIGN_UP;
import static com.getprepared.constant.UtilsConstant.REGEX.*;
import static com.getprepared.constant.WebConstants.REQUEST_ATTRIBUTES.*;

/**
 * Created by koval on 17.01.2017.
 */
public abstract class AbstractSignUpPageController extends AbstractController {

    protected void fillPage(final HttpServletRequest request) {
        request.setAttribute(TITLE, getMessages().getMessage(SIGN_UP, request.getLocale()));
        request.setAttribute(ROLES, Role.values());
        request.setAttribute(NAME_REGEX, NAME);
        request.setAttribute(SURNAME_REGEX, SURNAME);
        request.setAttribute(EMAIL_REGEX, EMAIL);
        request.setAttribute(PASSWORD_REGEX, PASSWORD);
    }
}
