package com.getprepared.controller.common;

import com.getprepared.annotation.Component;
import com.getprepared.annotation.Inject;
import com.getprepared.constant.PageConstants.ERRORS;
import com.getprepared.constant.PageConstants.LINKS;
import com.getprepared.constant.PageConstants.PAGES;
import com.getprepared.domain.Role;
import com.getprepared.domain.User;
import com.getprepared.exception.EntityExistsException;
import com.getprepared.exception.ValidationException;
import com.getprepared.service.UserService;
import com.getprepared.util.Validation;
import com.getprepared.util.impl.Messages;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.getprepared.constant.PageConstants.REDIRECT;
import static com.getprepared.constant.WebConstants.*;
import static com.getprepared.constant.WebConstants.REQUEST_ATTRIBUTES.ERROR_MSG;

/**
 * Created by koval on 17.01.2017.
 */
@Component("signUp")
public class SignUpController extends AbstractSignUpController {

    private static final Logger LOG = Logger.getLogger(SignUpController.class);

    @Inject
    private UserService userService;

    @Inject
    private Validation validation;

    @Inject
    private Messages messages;

    @Override
    public String execute(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        final User user = new User();
        try {
            fillUser(request, user);
            validation.validateUser(user);
            userService.signUp(user);

            if (user.getId() != null && user.getRole() == Role.STUDENT) {
                request.getSession().setAttribute(SESSION_ATTRIBUTES.STUDENT, user);
                response.sendRedirect(LINKS.STUDENT_HOME_PAGE);
                return REDIRECT;
            }

            if (user.getId() != null && user.getRole() == Role.TUTOR) {
                request.getSession().setAttribute(SESSION_ATTRIBUTES.TUTOR, user);
                response.sendRedirect(LINKS.TUTOR_QUIZZES);
                return REDIRECT;
            }
        } catch (final ValidationException e) {
            LOG.warn(e.getMessage(), e);
            request.setAttribute(ERROR_MSG, messages.getMessage(ERRORS.DATA_INVALIDATED, request.getLocale()));
        } catch (final EntityExistsException e) {
            LOG.warn(e.getMessage(), e);
            request.setAttribute(ERROR_MSG, messages.getMessage(ERRORS.USER_EXISTS, request.getLocale()));
        }
        request.setAttribute(REQUEST_ATTRIBUTES.USER, user);

        fillPage(request);
        return PAGES.SIGN_UP;
    }

    private void fillUser(final HttpServletRequest request, final User user) throws ValidationException {
        try {
            user.setName(request.getParameter(INPUTS.NAME));
            user.setSurname(request.getParameter(INPUTS.SURNAME));
            user.setEmail(request.getParameter(INPUTS.EMAIL));
            user.setRole(Role.valueOf(request.getParameter(INPUTS.ROLE)));
            user.setPassword(request.getParameter(INPUTS.PASSWORD));
        } catch (final RuntimeException e) {
            LOG.warn(e.getMessage(), e);
            throw new ValidationException("Failed to validate data", e);
        }
    }
}
