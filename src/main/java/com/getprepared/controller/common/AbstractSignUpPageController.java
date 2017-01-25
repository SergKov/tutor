package com.getprepared.controller.common;

import com.getprepared.controller.AbstractController;
import com.getprepared.domain.Role;
import com.getprepared.domain.User;
import com.getprepared.exception.ValidationException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import static com.getprepared.constant.PageConstants.NAMES.SIGN_UP;
import static com.getprepared.constant.UtilsConstant.REGEX;
import static com.getprepared.constant.WebConstants.INPUTS;
import static com.getprepared.constant.WebConstants.REQUEST_ATTRIBUTES.*;

/**
 * Created by koval on 17.01.2017.
 */
public abstract class AbstractSignUpPageController extends AbstractController {

    private static final Logger LOG = Logger.getLogger(AbstractSignUpPageController.class);

    protected void fillPage(final HttpServletRequest request) {
        request.setAttribute(TITLE, getMessages().getMessage(SIGN_UP, request.getLocale()));
        request.setAttribute(ROLES, Role.values());
        request.setAttribute(NAME_REGEX, REGEX.NAME);
        request.setAttribute(SURNAME_REGEX, REGEX.SURNAME);
        request.setAttribute(EMAIL_REGEX, REGEX.EMAIL);
        request.setAttribute(PASSWORD_REGEX, REGEX.PASSWORD);
    }

    protected User convertInputToUser(final HttpServletRequest request) throws ValidationException {
        try {
            final String role = request.getParameter(INPUTS.ROLE);
            final String name = request.getParameter(INPUTS.NAME);
            final String surname = request.getParameter(INPUTS.SURNAME);
            final String email = request.getParameter(INPUTS.EMAIL);
            final String password = request.getParameter(INPUTS.PASSWORD);
            return new User(Role.valueOf(role), name, surname, email, password);
        } catch (final Exception e) {
            LOG.warn(e.getMessage(), e);
            throw new ValidationException("Failed to validate data", e);
        }
    }
}
