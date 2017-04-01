package com.getprepared.web.command.tutor;

import com.getprepared.web.annotation.CommandMapping;
import com.getprepared.web.annotation.Controller;
import com.getprepared.web.command.common.AbstractUpdatePasswordCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.getprepared.web.constant.PageConstant.*;

/**
 * Created by koval on 31.03.2017.
 */
@Controller
@CommandMapping(COMMAND.TUTOR_UPDATE_PASSWORD)
public class TutorUpdatePasswordCommand extends AbstractUpdatePasswordCommand {

    @Override
    public String execute(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        fillPage(request);
        return PAGE.TUTOR_SETTINGS;
    }
}
