package com.getprepared.controller.common.signUp;

import com.getprepared.constant.PageConstants;
import com.getprepared.constant.PageConstants.LINKS;
import com.getprepared.constant.ServerConstants;
import com.getprepared.constant.WebConstants;
import com.getprepared.domain.Role;
import com.getprepared.domain.User;
import com.getprepared.exception.EntityExistsException;
import com.getprepared.exception.ValidationException;
import com.getprepared.service.UserService;
import com.getprepared.service.impl.ServiceFactory;
import com.getprepared.utils.impl.Messages;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static com.getprepared.constant.PageConstants.*;
import static com.getprepared.constant.PageConstants.REDIRECT;
import static com.getprepared.constant.ServerConstants.*;
import static com.getprepared.constant.WebConstants.*;
import static com.getprepared.constant.WebConstants.INPUTS.*;
import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.ErrorMsg.ERROR_MSG;

/**
 * Created by koval on 17.01.2017.
 */
public class SignUpController extends AbstractSignUpPageController {

    private static final Logger LOG = Logger.getLogger(SignUpController.class);

    private UserService userService;

    @Override
    public void init() {
        userService = ServiceFactory.getInstance().getService(SERVICES.USER_SERVICE, UserService.class);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        final User user = new User();
        user.setRole(Role.valueOf(request.getParameter(ROLE)));
        user.setName(request.getParameter(NAME));
        user.setSurname(request.getParameter(SURNAME));
        user.setEmail(request.getParameter(EMAIL));
        user.setPassword(request.getParameter(PASSWORD));

        try {
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
