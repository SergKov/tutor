package com.getprepared.web.command.tutor;

import com.getprepared.annotation.Inject;
import com.getprepared.core.converter.impl.UserUpdatePasswordConverter;
import com.getprepared.core.exception.EntityNotFoundException;
import com.getprepared.core.service.UserService;
import com.getprepared.core.util.Messages;
import com.getprepared.persistence.domain.User;
import com.getprepared.web.annotation.CommandMapping;
import com.getprepared.web.annotation.Controller;
import com.getprepared.web.command.common.AbstractUpdatePasswordCommand;
import com.getprepared.web.constant.WebConstant;
import com.getprepared.web.form.UserUpdatePasswordForm;
import com.getprepared.web.validation.ValidationService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

import static com.getprepared.web.constant.PageConstant.*;
import static com.getprepared.web.constant.WebConstant.REQUEST_ATTRIBUTE.ERROR_MSG;
import static com.getprepared.web.constant.WebConstant.REQUEST_ATTRIBUTE.ERROR_MSGS;
import static org.apache.commons.collections4.MapUtils.isEmpty;

/**
 * Created by koval on 31.03.2017.
 */
@Controller
@CommandMapping(COMMAND.TUTOR_UPDATE_PASSWORD)
public class TutorUpdatePasswordCommand extends AbstractUpdatePasswordCommand {

    private static final Logger LOG = Logger.getLogger(TutorUpdatePasswordCommand.class);

    @Inject
    private UserService userService;

    @Inject
    private ValidationService validationService;

    @Inject
    private Messages messages;

    @Inject
    private UserUpdatePasswordConverter userUpdatePasswordConverter;

    @Override
    public String execute(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        final User user = (User) request.getSession().getAttribute(WebConstant.SESSION_ATTRIBUTE.STUDENT);

        final String oldPassword = request.getParameter(WebConstant.INPUT.OLD_PASSWORD);
        final String newPassword = request.getParameter(WebConstant.INPUT.NEW_PASSWORD);

        final UserUpdatePasswordForm form = new UserUpdatePasswordForm();
        form.setNewPassword(newPassword);

        final Map<String, String> errorMsgs = validationService.validate(form);
        if (isEmpty(errorMsgs)) {
            try {
                userService.updateTutorPassword(user.getEmail(), oldPassword, newPassword);
            } catch (final EntityNotFoundException e) {
                LOG.warn(e.getMessage(), e);
                request.setAttribute(ERROR_MSG, messages.getMessage(ERROR.OLD_PASSWORD_INCORRECT, request.getLocale()));
            }
        } else {
            request.setAttribute(ERROR_MSGS, errorMsgs);
        }

        fillPage(request);
        return PAGE.TUTOR_UPDATE_PASSWORD;
    }
}
