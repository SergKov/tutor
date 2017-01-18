package com.getprepared.controller.student.home_page;

import com.getprepared.domain.User;
import com.getprepared.exception.EntityNotFoundException;
import com.getprepared.exception.ValidationException;
import com.getprepared.service.UserService;
import com.getprepared.service.impl.ServiceFactory;
import com.getprepared.utils.impl.Messages;
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
 * Created by koval on 15.01.2017.
 */
public class StudentSignInController extends AbstractHomePageController {

    private static final Logger LOG = Logger.getLogger(StudentSignInController.class);

    private UserService userService;

    @Override
    public void init() {
        userService = ServiceFactory.getInstance().getService(USER_SERVICE, UserService.class);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        final HttpSession httpSession = request.getSession();
        if (httpSession.getAttribute(SESSION_ATTRIBUTES.STUDENT) != null) {
            response.sendRedirect(LINKS.CHOOSE_TEST);
            return REDIRECT;
        }

        final String email = request.getParameter(INPUTS.EMAIL);
        final String password = request.getParameter(INPUTS.PASSWORD);

        try {
            final User user = userService.signIn(email, password);
            if (user != null) {
                httpSession.setAttribute(SESSION_ATTRIBUTES.STUDENT, user);
                response.sendRedirect(LINKS.CHOOSE_TEST);
                return REDIRECT;
            }
        } catch (final ValidationException e) {
            request.setAttribute(ERROR_MSG, getMessages().getMessage(ERRORS.CREDENTIALS_INVALIDATED,
                    request.getLocale()));
            LOG.warn(e.getMessage(), e);
        } catch (final EntityNotFoundException e) {
            request.setAttribute(ERROR_MSG, getMessages().getMessage(ERRORS.STUDENT_IS_NOT_EXIST, request.getLocale()));
            LOG.warn(e.getMessage(), e);
        }

        request.setAttribute(TITLE, NAMES.SIGN_IN);
        fillPage(request);

        return PAGES.HOME;
    }
}
