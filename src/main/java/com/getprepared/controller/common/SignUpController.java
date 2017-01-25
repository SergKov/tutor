package com.getprepared.controller.common;

import com.getprepared.constant.PageConstants.ERRORS;
import com.getprepared.constant.PageConstants.LINKS;
import com.getprepared.constant.PageConstants.PAGES;
import com.getprepared.domain.Role;
import com.getprepared.domain.User;
import com.getprepared.exception.EntityExistsException;
import com.getprepared.exception.ValidationException;
import com.getprepared.service.UserService;
import com.getprepared.utils.Validation;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.getprepared.constant.PageConstants.REDIRECT;
import static com.getprepared.constant.ServerConstants.SERVICES.USER_SERVICE;
import static com.getprepared.constant.UtilsConstant.VALIDATION;
import static com.getprepared.constant.WebConstants.INPUTS.*;
import static com.getprepared.constant.WebConstants.REQUEST_ATTRIBUTES;
import static com.getprepared.constant.WebConstants.REQUEST_ATTRIBUTES.ERROR_MSG;
import static com.getprepared.constant.WebConstants.SESSION_ATTRIBUTES;

/**
 * Created by koval on 17.01.2017.
 */
public class SignUpController extends AbstractSignUpPageController {

    private static final Logger LOG = Logger.getLogger(SignUpController.class);

    private UserService userService;
    private Validation validation;

    @Override
    public void init() {
        userService = getServiceFactory().getService(USER_SERVICE, UserService.class);
        validation = getUtilsFactory().getUtil(VALIDATION, Validation.class);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            final User user = convertInputToUser(request);
            validation.validateUser(user);
            request.setAttribute(REQUEST_ATTRIBUTES.USER, user);

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
            request.setAttribute(ERROR_MSG, getMessages().getMessage(ERRORS.DATA_INVALIDATED, request.getLocale()));
            LOG.warn(e.getMessage(), e);
        } catch (final EntityExistsException e) {
            request.setAttribute(ERROR_MSG, getMessages().getMessage(ERRORS.USER_EXISTS, request.getLocale()));
            LOG.warn(e.getMessage(), e);
        }

        fillPage(request);
        return PAGES.SIGN_UP;
    }
}
