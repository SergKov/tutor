package com.getprepared.controller.tutor.sign_in;

import com.getprepared.controller.common.abstract_classes.AbstractSignInController;
import com.getprepared.domain.User;
import com.getprepared.exception.EntityNotFoundException;
import com.getprepared.exception.ValidationException;
import com.getprepared.service.UserService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.getprepared.constant.PageConstants.*;
import static com.getprepared.constant.ServerConstants.SERVICES.USER_SERVICE;
import static com.getprepared.constant.WebConstants.INPUTS;
import static com.getprepared.constant.WebConstants.REQUEST_ATTRIBUTES.ERROR_MSG;
import static com.getprepared.constant.WebConstants.REQUEST_ATTRIBUTES.TITLE;
import static com.getprepared.constant.WebConstants.SESSION_ATTRIBUTES;

/**
 * Created by koval on 21.01.2017.
 */
public class TutorSignInController extends AbstractSignInController {

    private static final Logger LOG = Logger.getLogger(TutorSignInController.class);

    private UserService userService;

    @Override
    public void init() {
        userService = getServiceFactory().getService(USER_SERVICE, UserService.class);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        final HttpSession httpSession = request.getSession();
        if (httpSession.getAttribute(SESSION_ATTRIBUTES.TUTOR) != null) {
            response.sendRedirect(LINKS.TUTOR_SPECIALITIES);
            return REDIRECT;
        }

        final String email = request.getParameter(INPUTS.EMAIL);
        final String password = request.getParameter(INPUTS.PASSWORD);

        try {
            final User tutor = userService.signIn(email, password);
            if (tutor != null) {
                httpSession.setAttribute(SESSION_ATTRIBUTES.TUTOR, tutor);
                response.sendRedirect(LINKS.TUTOR_SPECIALITIES);
                return REDIRECT;
            }
        } catch (final EntityNotFoundException e) {
            request.setAttribute(ERROR_MSG, getMessages().getMessage(ERRORS.CREDENTIALS_INVALIDATED,
                    request.getLocale()));
            LOG.warn(e.getMessage(), e);
        } catch (final ValidationException e) {
            request.setAttribute(ERROR_MSG, getMessages().getMessage(ERRORS.STUDENT_IS_NOT_EXISTS,
                    request.getLocale()));
            LOG.warn(e.getMessage(), e);
        }

        request.setAttribute(TITLE, getMessages().getMessage(NAMES.SIGN_IN, request.getLocale()));
        fillPage(request);

        return PAGES.TUTOR_SIGN_IN;
    }
}
