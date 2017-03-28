package com.getprepared.web.command.common;

import com.getprepared.annotation.Inject;
import com.getprepared.core.converter.Converter;
import com.getprepared.core.exception.EntityExistsException;
import com.getprepared.core.service.UserService;
import com.getprepared.core.util.Messages;
import com.getprepared.persistence.domain.Role;
import com.getprepared.persistence.domain.User;
import com.getprepared.web.annotation.Controller;
import com.getprepared.web.annotation.CommandMapping;
import com.getprepared.web.constant.PageConstant.ERROR;
import com.getprepared.web.constant.PageConstant.LINK;
import com.getprepared.web.constant.PageConstant.PAGE;
import com.getprepared.web.form.UserSignUpForm;
import com.getprepared.web.validation.ValidationService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

import static com.getprepared.web.constant.PageConstant.COMMAND;
import static com.getprepared.web.constant.PageConstant.REDIRECT;
import static com.getprepared.web.constant.WebConstant.*;
import static com.getprepared.web.constant.WebConstant.REQUEST_ATTRIBUTE.ERROR_MSG;
import static com.getprepared.web.constant.WebConstant.REQUEST_ATTRIBUTE.ERROR_MSGS;
import static org.apache.commons.collections4.MapUtils.isNotEmpty;

/**
 * Created by koval on 17.01.2017.
 */
@Controller
@CommandMapping(COMMAND.SIGN_UP)
public class SignUpCommand extends AbstractSignUpCommand {

    private static final Logger LOG = Logger.getLogger(SignUpCommand.class);

    @Inject
    private UserService userService;

    @Inject
    private ValidationService validationService;

    @Inject
    private Converter<UserSignUpForm, User> userSignUpConverter;

    @Inject
    private Messages messages;

    @Override
    public String execute(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        final UserSignUpForm userForm = new UserSignUpForm();
        fillForm(request, userForm);
        final Map<String, String> errors = validationService.validate(userForm);
        if (isNotEmpty(errors)) {
            request.setAttribute(ERROR_MSGS, messages.getMessages(errors, request.getLocale()));
        } else {
            User user = null;
            try {
                user = userSignUpConverter.convert(userForm);
                userService.signUp(user);

                if (user.getId() != null && user.getRole() == Role.STUDENT) {
                    request.getSession().setAttribute(SESSION_ATTRIBUTE.STUDENT, user);
                    response.sendRedirect(LINK.STUDENT_HOME_PAGE);
                    return REDIRECT;
                }

                if (user.getId() != null && user.getRole() == Role.TUTOR) {
                    request.getSession().setAttribute(SESSION_ATTRIBUTE.TUTOR, user);
                    response.sendRedirect(LINK.TUTOR_QUIZZES);
                    return REDIRECT;
                }
            } catch (final EntityExistsException e) {
                LOG.warn(e.getMessage(), e);
                request.setAttribute(ERROR_MSG, messages.getMessage(ERROR.USER_EXISTS, request.getLocale()));
            }
        }
        request.setAttribute(REQUEST_ATTRIBUTE.USER, userForm);
        fillPage(request);
        return PAGE.SIGN_UP;
    }

    private void fillForm(final HttpServletRequest request, final UserSignUpForm userSignUpForm) {
        userSignUpForm.setName(request.getParameter(INPUT.NAME));
        userSignUpForm.setSurname(request.getParameter(INPUT.SURNAME));
        userSignUpForm.setEmail(request.getParameter(INPUT.EMAIL));
        userSignUpForm.setRole(request.getParameter(INPUT.ROLE));
        userSignUpForm.setPassword(request.getParameter(INPUT.PASSWORD));
    }
}
