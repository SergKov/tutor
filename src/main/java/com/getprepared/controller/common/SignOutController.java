package com.getprepared.controller.common;

import com.getprepared.constant.PageConstants;
import com.getprepared.controller.AbstractController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.getprepared.constant.PageConstants.*;
import static com.getprepared.constant.PageConstants.PAGES;
import static com.getprepared.constant.WebConstants.SESSION_ATTRIBUTES.STUDENT;
import static com.getprepared.constant.WebConstants.SESSION_ATTRIBUTES.TUTOR;

/**
 * Created by koval on 22.01.2017.
 */
public class SignOutController extends AbstractController {

    @Override
    public void init() { }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final HttpSession httpSession = request.getSession(false);

        if (httpSession.getAttribute(STUDENT) != null) {
            httpSession.invalidate();
            response.sendRedirect(LINKS.STUDENT_SIGN_IN);
            return REDIRECT;
        }

        httpSession.invalidate();
        response.sendRedirect(LINKS.TUTOR_SIGN_IN);
        return REDIRECT;
    }
}
