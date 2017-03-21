package com.getprepared.web.controller.student;

import com.getprepared.annotation.Component;
import com.getprepared.web.constant.PageConstants.PAGES;
import com.getprepared.web.controller.common.AbstractSignInController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by koval on 14.01.2017.
 */
@Component("studentSignInPage")
public class StudentSignInPageController extends AbstractSignInController {

    @Override
    public String execute(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        fillPage(request);
        return PAGES.STUDENT_SIGN_IN;
    }
}