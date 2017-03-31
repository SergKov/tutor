package com.getprepared.web.command.student;

import com.getprepared.annotation.Inject;
import com.getprepared.core.service.UserService;
import com.getprepared.web.annotation.CommandMapping;
import com.getprepared.web.annotation.Controller;
import com.getprepared.web.command.common.AbstractSettingsCommand;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.getprepared.web.constant.PageConstant.*;

/**
 * Created by koval on 31.03.2017.
 */
@Controller
@CommandMapping(COMMAND.STUDENT_SETTINGS)
public class StudentSettingsCommand extends AbstractSettingsCommand {

    private static final Logger LOG = Logger.getLogger(StudentSettingsCommand.class);

    @Inject
    private UserService userService;

    @Override
    public String execute(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        fillPage(request);
        return PAGE.STUDENT_SETTINGS;
    }
}
