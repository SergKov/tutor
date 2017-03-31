package com.getprepared.web.command.student;

import com.getprepared.annotation.Inject;
import com.getprepared.core.service.UserService;
import com.getprepared.persistence.domain.User;
import com.getprepared.web.annotation.CommandMapping;
import com.getprepared.web.annotation.Controller;
import com.getprepared.web.command.common.AbstractSettingsCommand;
import com.getprepared.web.constant.WebConstant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.getprepared.web.constant.PageConstant.LINK;
import static com.getprepared.web.constant.PageConstant.PAGE;
import static com.getprepared.web.constant.WebConstant.*;

/**
 * Created by koval on 31.03.2017.
 */
@Controller
@CommandMapping(LINK.STUDENT_SETTINGS)
public class StudentSettingsPageCommand extends AbstractSettingsCommand {

    @Inject
    private UserService userService;



    @Override
    public String execute(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        final User user = (User) request.getSession().getAttribute(SESSION_ATTRIBUTE.STUDENT);

        final String newEmail = request.getParameter(INPUT.EMAIL);
        final String oldPassword = request.getParameter(INPUT.OLD_PASSWORD);
        final String newPassword = request.getParameter(INPUT.NEW_PASSWORD);


        fillPage(request);
        return PAGE.STUDENT_SETTINGS;
    }
}
