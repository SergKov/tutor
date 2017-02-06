package com.getprepared.controller.student;

import com.getprepared.constant.PageConstants;
import com.getprepared.constant.WebConstants;
import com.getprepared.controller.AbstractController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by koval on 06.02.2017.
 */
public class StudentResultPageController extends AbstractController {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().getAttribute(WebConstants.SESSION_ATTRIBUTES.MARK);
        return PageConstants.PAGES.STUDENT_GET_RESULT;
    }
}
