package com.getprepared.web.command.tutor;

import com.getprepared.web.annotation.Controller;
import com.getprepared.web.annotation.CommandMapping;
import com.getprepared.web.command.common.AbstractSignInCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.getprepared.web.constant.PageConstant.LINK;
import static com.getprepared.web.constant.PageConstant.PAGE;

/**
 * Created by koval on 21.01.2017.
 */
@Controller
@CommandMapping(LINK.TUTOR_SIGN_IN)
public class TutorSignInPageCommand extends AbstractSignInCommand {

    @Override
    public String execute(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        fillPage(request);
        return PAGE.TUTOR_SIGN_IN;
    }
}
