package com.getprepared.web.command.student;

import com.getprepared.web.annotation.Controller;
import com.getprepared.web.constant.PageConstants.PAGES;
import com.getprepared.web.command.common.AbstractSignInCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by koval on 14.01.2017.
 */
@Controller
public class StudentSignInPageController extends AbstractSignInCommand {

    @Override
    public String execute(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        fillPage(request);
        return PAGES.STUDENT_SIGN_IN;
    }
}