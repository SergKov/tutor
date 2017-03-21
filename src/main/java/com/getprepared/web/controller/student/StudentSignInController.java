package com.getprepared.web.controller.student;

import com.getprepared.annotation.Component;
import com.getprepared.annotation.Inject;
import com.getprepared.core.exception.EntityNotFoundException;
import com.getprepared.core.service.UserService;
import com.getprepared.core.util.Messages;
import com.getprepared.persistence.domain.Role;
import com.getprepared.persistence.domain.User;
import com.getprepared.web.controller.common.AbstractSignInController;
import com.getprepared.web.validation.ValidationService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.getprepared.web.constant.PageConstants.*;
import static com.getprepared.web.constant.WebConstants.*;
import static com.getprepared.web.constant.WebConstants.REQUEST_ATTRIBUTES.ERROR_MSG;

/**
 * Created by koval on 15.01.2017.
 */
@Component("studentSignIn")
public class StudentSignInController extends AbstractSignInController {

    private static final Logger LOG = Logger.getLogger(StudentSignInController.class);

    @Inject
    private UserService userService;

    @Inject
    private ValidationService validationService;

    @Inject
    private Messages messages;

    @Override
    public String execute(final HttpServletRequest request, final HttpServletResponse response) throws IOException {

        final String email = request.getParameter(INPUTS.EMAIL);
        final String password = request.getParameter(INPUTS.PASSWORD);

        try {
            final User user = userService.signInStudent(email, password);
            if (user != null && user.getRole() == Role.STUDENT) {
                request.getSession().setAttribute(SESSION_ATTRIBUTES.STUDENT, user);
                response.sendRedirect(LINKS.STUDENT_HOME_PAGE);
                return REDIRECT;
            }
        } catch (final EntityNotFoundException e) {
            LOG.warn(e.getMessage(), e);
            request.setAttribute(REQUEST_ATTRIBUTES.EMAIL, email);
            request.setAttribute(ERROR_MSG, messages.getMessage(ERRORS.USER_NOT_FOUND, request.getLocale()));
        }

        fillPage(request);
        return PAGES.STUDENT_SIGN_IN;
    }
}
