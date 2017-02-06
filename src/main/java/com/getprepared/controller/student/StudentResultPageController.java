package com.getprepared.controller.student;

import com.getprepared.constant.PageConstants;
import com.getprepared.controller.AbstractController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.getprepared.constant.PageConstants.REDIRECT;
import static com.getprepared.constant.WebConstants.SESSION_ATTRIBUTES;

/**
 * Created by koval on 06.02.2017.
 */
public class StudentResultPageController extends AbstractController {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (request.getSession().getAttribute(SESSION_ATTRIBUTES.MARK) == null) {
            response.sendRedirect(PageConstants.LINKS.NOT_FOUND);
            return REDIRECT;
        }
        request.getSession().getAttribute(SESSION_ATTRIBUTES.MARK);
        return PageConstants.PAGES.STUDENT_GET_RESULT;
    }
}
