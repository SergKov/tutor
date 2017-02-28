package com.getprepared.controller.student;

import com.getprepared.annotation.Bean;
import com.getprepared.annotation.Inject;
import com.getprepared.controller.Controller;
import com.getprepared.util.impl.Messages;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.getprepared.constant.PageConstants.*;
import static com.getprepared.constant.WebConstants.REQUEST_ATTRIBUTES.TITLE;
import static com.getprepared.constant.WebConstants.SESSION_ATTRIBUTES;

/**
 * Created by koval on 06.02.2017.
 */
@Bean("studentResultPage")
public class StudentResultPageController implements Controller {

    @Inject
    private Messages messages;

    @Override
    public String execute(final HttpServletRequest request, final HttpServletResponse response) throws IOException {

        if (request.getSession().getAttribute(SESSION_ATTRIBUTES.MARK) == null) {
            response.sendRedirect(LINKS.NOT_FOUND);
            return REDIRECT;
        }

        request.setAttribute(TITLE, messages.getMessage(NAMES.RESULT, request.getLocale()));
        request.getSession().getAttribute(SESSION_ATTRIBUTES.MARK);
        return PAGES.STUDENT_GET_RESULT;
    }
}
