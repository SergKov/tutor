package com.getprepared.web.command.student;

import com.getprepared.annotation.Inject;
import com.getprepared.core.exception.EntityNotFoundException;
import com.getprepared.core.service.UserService;
import com.getprepared.core.util.Messages;
import com.getprepared.persistence.domain.Role;
import com.getprepared.persistence.domain.User;
import com.getprepared.web.annotation.CommandMapping;
import com.getprepared.web.annotation.Controller;
import com.getprepared.web.command.common.AbstractSignInCommand;
import com.getprepared.web.validation.ValidationService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.getprepared.web.constant.ApplicationConstant.*;
import static com.getprepared.web.constant.PropertyConstant.KEY.USER_NOT_FOUND;
import static com.getprepared.web.constant.WebConstant.*;
import static com.getprepared.web.constant.WebConstant.REQUEST_ATTRIBUTE.ERROR_MSG;

/**
 * Created by koval on 15.01.2017.
 */
@Controller
@CommandMapping(COMMAND.STUDENT_SIGN_IN)
public class StudentSignInCommand extends AbstractSignInCommand {

    private static final Logger LOG = Logger.getLogger(StudentSignInCommand.class);

    @Inject
    private UserService userService;

    @Inject
    private ValidationService validationService;

    @Inject
    private Messages messages;

    @Override
    public String execute(final HttpServletRequest request, final HttpServletResponse response) throws IOException {

        final String email = request.getParameter(INPUT.EMAIL);
        final String password = request.getParameter(INPUT.PASSWORD);

        try {
            final User user = userService.signInStudent(email, password);
            if (user != null && user.getRole() == Role.STUDENT) {
                request.getSession().setAttribute(SESSION_ATTRIBUTE.STUDENT, user);
                response.sendRedirect(LINK.STUDENT_HOME_PAGE);
                return REDIRECT;
            }
        } catch (final EntityNotFoundException e) {
            LOG.warn(e.getMessage(), e);
            request.setAttribute(ERROR_MSG, messages.getMessage(USER_NOT_FOUND, request.getLocale()));
        }

        request.setAttribute(REQUEST_ATTRIBUTE.EMAIL, email);
        fillPage(request);
        return PATH.STUDENT_SIGN_IN;
    }
}
