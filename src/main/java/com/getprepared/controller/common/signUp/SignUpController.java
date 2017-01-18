package com.getprepared.controller.common.signUp;

import com.getprepared.constant.PageConstants.*;
import com.getprepared.domain.Role;
import com.getprepared.domain.User;
import com.getprepared.exception.EntityExistsException;
import com.getprepared.exception.ValidationException;
import com.getprepared.service.UserService;
import com.getprepared.service.impl.ServiceFactory;
import com.getprepared.utils.UtilsFactory;
import com.getprepared.utils.Validation;
import com.getprepared.utils.impl.ValidationImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.getprepared.constant.PageConstants.*;
import static com.getprepared.constant.ServerConstants.SERVICES;
import static com.getprepared.constant.UtilsConstant.FORM_VALIDATION;
import static com.getprepared.constant.WebConstants.INPUTS.*;
import static com.getprepared.constant.WebConstants.SESSION_ATTRIBUTES;
import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.ErrorMsg.ERROR_MSG;

/**
 * Created by koval on 17.01.2017.
 */
public class SignUpController extends AbstractSignUpPageController {

    private static final Logger LOG = Logger.getLogger(SignUpController.class);

    private UserService userService;
    private Validation validation;

    @Override
    public void init() {
        userService = ServiceFactory.getInstance().getService(SERVICES.USER_SERVICE, UserService.class);
        validation = UtilsFactory.getInstance().getUtil(FORM_VALIDATION, ValidationImpl.class);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        final User user = new User();

        try {

            final String role = request.getParameter(ROLE);
            validation.validateRole(role);
            user.setRole(Role.valueOf(role));

            final String name = request.getParameter(NAME);
            validation.validateName(name);
            user.setName(request.getParameter(NAME));

            final String surname = request.getParameter(SURNAME);
            validation.validateSurname(surname);
            user.setSurname(request.getParameter(SURNAME));

            final String email = request.getParameter(EMAIL);
            validation.validateEmail(email);
            user.setEmail(email);

            final String password = request.getParameter(PASSWORD);
            validation.validatePassword(password);
            user.setPassword(password);

            userService.signUp(user);

            if (user.getId() != null && user.getRole() == Role.STUDENT) {
                request.getSession().setAttribute(SESSION_ATTRIBUTES.STUDENT, user);
                response.sendRedirect(LINKS.CHOOSE_TEST);
                return REDIRECT;
            }

            if (user.getId() != null && user.getRole() == Role.TUTOR) {
                request.getSession().setAttribute(SESSION_ATTRIBUTES.TUTOR, user);
                response.sendRedirect(LINKS.SPECIALITIES);
                return REDIRECT;
            }
        } catch (final ValidationException e) {
            request.setAttribute(ERROR_MSG, ERRORS.DATA_INVALIDATED);
            LOG.warn(e.getMessage(), e);
        } catch (final EntityExistsException e) {
            request.setAttribute(ERROR_MSG, ERRORS.STUDENT_EXISTS);
            LOG.warn(e.getMessage(), e);
        }

        fillPage(request);

        return PAGES.SIGN_UP;
    }
}
