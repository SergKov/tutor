package com.getprepared.web.command.student;

import com.getprepared.web.annotation.Controller;
import com.getprepared.web.annotation.CommandMapping;
import com.getprepared.web.command.common.AbstractSignInCommand;
import com.getprepared.web.constant.PageConstant.PAGE;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.getprepared.web.constant.PageConstant.LINK;

/**
 * Created by koval on 14.01.2017.
 */
@Controller
@CommandMapping(LINK.STUDENT_SIGN_IN)
public class StudentSignInPageCommand extends AbstractSignInCommand {

    @Override
    public String execute(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        fillPage(request);
        return PAGE.STUDENT_SIGN_IN;
    }
}