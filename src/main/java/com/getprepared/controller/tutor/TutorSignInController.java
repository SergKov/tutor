package com.getprepared.controller.tutor;

import com.getprepared.annotation.Bean;
import com.getprepared.annotation.Inject;
import com.getprepared.controller.common.AbstractSignInController;
import com.getprepared.domain.User;
import com.getprepared.exception.EntityNotFoundException;
import com.getprepared.exception.ValidationException;
import com.getprepared.service.UserService;
import com.getprepared.util.Validation;
import com.getprepared.util.impl.Messages;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.getprepared.constant.PageConstants.*;
import static com.getprepared.constant.WebConstants.*;
import static com.getprepared.constant.WebConstants.REQUEST_ATTRIBUTES.ERROR_MSG;

/**
 * Created by koval on 21.01.2017.
 */
@Bean("tutorSignIn")
public class TutorSignInController extends AbstractSignInController {

    private static final Logger LOG = Logger.getLogger(TutorSignInController.class);

    @Inject
    private UserService userService;

    @Inject
    private Validation validation;

    @Inject
    private Messages messages;

    @Override
    public String execute(final HttpServletRequest request, final HttpServletResponse response) throws IOException {

        final String email = request.getParameter(INPUTS.EMAIL);
        final String password = request.getParameter(INPUTS.PASSWORD);

        try {
            validation.validateEmail(email);
            validation.validatePassword(password);

            final User user = userService.signInTutor(email, password);

            final HttpSession httpSession = request.getSession();

            if (user != null) {
                httpSession.setAttribute(SESSION_ATTRIBUTES.TUTOR, user);
                response.sendRedirect(LINKS.TUTOR_QUIZZES);
                return REDIRECT;
            }

        } catch (final EntityNotFoundException e) {
            LOG.warn(e.getMessage(), e);
            request.setAttribute(ERROR_MSG, messages.getMessage(ERRORS.TUTOR_NOT_FOUND, request.getLocale()));
        } catch (final ValidationException e) {
            LOG.warn(e.getMessage(), e);
            request.setAttribute(ERROR_MSG, messages.getMessage(ERRORS.CREDENTIALS_INVALIDATED,
                    request.getLocale()));
        }

        request.setAttribute(REQUEST_ATTRIBUTES.EMAIL, email);
        fillPage(request);
        return PAGES.TUTOR_SIGN_IN;
    }
}
