package com.getprepared.controller.student.sign_in;

import com.getprepared.constant.PageConstants.PAGES;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.getprepared.constant.PageConstants.NAMES.SIGN_IN;
import static com.getprepared.constant.WebConstants.REQUEST_ATTRIBUTES.TITLE;

/**
 * Created by koval on 14.01.2017.
 */
public class StudentSignInPageController extends AbstractSignInController {

    @Override
    public void init() { }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

//        if (request.getSession(false) != null) {
//            response.sendRedirect(LINKS.STUDENT_HOME_PAGE);
//            return REDIRECT;
//        }

        request.setAttribute(TITLE, SIGN_IN);
        fillPage(request);
        return PAGES.STUDENT_SIGN_IN;
    }
}