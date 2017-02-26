package com.getprepared.controller.student;

import com.getprepared.annotation.Bean;
import com.getprepared.controller.AbstractController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.getprepared.constant.PageConstants.*;
import static com.getprepared.constant.WebConstants.REQUEST_ATTRIBUTES.TITLE;
import static com.getprepared.constant.WebConstants.SESSION_ATTRIBUTES;

/**
 * Created by koval on 06.02.2017.
 */
@Bean("studentResultPageController")
public class StudentResultPageController extends AbstractController {

    @Override
    public String execute(final HttpServletRequest request, final HttpServletResponse response) throws IOException {

        if (request.getSession().getAttribute(SESSION_ATTRIBUTES.MARK) == null) {
            response.sendRedirect(LINKS.NOT_FOUND);
            return REDIRECT;
        }

        request.setAttribute(TITLE, getMessages().getMessage(NAMES.RESULT, request.getLocale()));
        request.getSession().getAttribute(SESSION_ATTRIBUTES.MARK);
        return PAGES.STUDENT_GET_RESULT;
    }
}
