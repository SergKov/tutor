package com.getprepared.controller.student;

import com.getprepared.annotation.Component;
import com.getprepared.annotation.Inject;
import com.getprepared.controller.common.AbstractSignInController;
import com.getprepared.domain.Role;
import com.getprepared.domain.User;
import com.getprepared.exception.EntityNotFoundException;
import com.getprepared.service.UserService;
import com.getprepared.util.Messages;
import com.getprepared.validation.ValidationService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.getprepared.constant.PageConstants.*;
import static com.getprepared.constant.WebConstants.*;
import static com.getprepared.constant.WebConstants.REQUEST_ATTRIBUTES.ERROR_MSG;

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

        //TODO validation

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
