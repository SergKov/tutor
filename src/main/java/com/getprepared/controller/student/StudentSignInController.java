package com.getprepared.controller.student;

import com.getprepared.controller.common.AbstractSignInController;
import com.getprepared.domain.Role;
import com.getprepared.domain.User;
import com.getprepared.exception.EntityNotFoundException;
import com.getprepared.exception.ValidationException;
import com.getprepared.service.UserService;
import com.getprepared.utils.Validation;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.getprepared.constant.PageConstants.*;
import static com.getprepared.constant.ServerConstants.SERVICES.USER_SERVICE;
import static com.getprepared.constant.UtilsConstant.VALIDATION;
import static com.getprepared.constant.WebConstants.*;
import static com.getprepared.constant.WebConstants.REQUEST_ATTRIBUTES.ERROR_MSG;

/**
 * Created by koval on 15.01.2017.
 */
public class StudentSignInController extends AbstractSignInController {

    private static final Logger LOG = Logger.getLogger(StudentSignInController.class);

    private UserService userService;
    private Validation validation;

    @Override
    public void init() {
        userService = getServiceFactory().getService(USER_SERVICE, UserService.class);
        validation = getUtilsFactory().getUtil(VALIDATION, Validation.class);
    }

    @Override
    public String execute(final HttpServletRequest request, final HttpServletResponse response) throws IOException {

        final HttpSession httpSession = request.getSession();
        if (httpSession.getAttribute(SESSION_ATTRIBUTES.STUDENT) != null) {
            response.sendRedirect(LINKS.STUDENT_HOME_PAGE);
            return REDIRECT;
        }

        final String email = request.getParameter(INPUTS.EMAIL);
        final String password = request.getParameter(INPUTS.PASSWORD);

        request.setAttribute(REQUEST_ATTRIBUTES.EMAIL, email);

        try {
            validation.validateEmail(email);
            validation.validatePassword(password);

            final User user = userService.signInStudent(email, password);
            if (user != null && user.getRole() == Role.STUDENT) {
                httpSession.setAttribute(SESSION_ATTRIBUTES.STUDENT, user);
                response.sendRedirect(LINKS.STUDENT_HOME_PAGE);
                return REDIRECT;
            }
        } catch (final ValidationException e) {
            LOG.warn(e.getMessage(), e);
            request.setAttribute(ERROR_MSG, getMessages().getMessage(ERRORS.CREDENTIALS_INVALIDATED,
                    request.getLocale()));
        } catch (final EntityNotFoundException e) {
            LOG.warn(e.getMessage(), e);
            request.setAttribute(ERROR_MSG, getMessages().getMessage(ERRORS.USER_NOT_FOUND, request.getLocale()));
        }

        fillPage(request);
        return PAGES.STUDENT_SIGN_IN;
    }
}
