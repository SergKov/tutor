package com.getprepared.web.command.student;

import com.getprepared.web.annotation.CommandMapping;
import com.getprepared.web.annotation.Controller;
import com.getprepared.web.command.common.AbstractSignInCommand;
import com.getprepared.web.constant.ApplicationConstant.PATH;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.getprepared.web.constant.ApplicationConstant.LINK;

/**
 * Created by koval on 14.01.2017.
 */
@Controller
@CommandMapping(LINK.STUDENT_SIGN_IN)
public class StudentSignInPageCommand extends AbstractSignInCommand {

    @Override
    public String execute(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        fillPage(request);
        return PATH.STUDENT_SIGN_IN;
    }
}