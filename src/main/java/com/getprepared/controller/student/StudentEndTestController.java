package com.getprepared.controller.student;

import com.getprepared.controller.AbstractController;
import com.getprepared.controller.dto.TestQuestion;
import com.getprepared.service.QuestionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.getprepared.constant.PageConstants.*;
import static com.getprepared.constant.ServerConstants.SERVICES.QUESTION_SERVICE;
import static com.getprepared.constant.WebConstants.*;

/**
 * Created by koval on 05.02.2017.
 */
public class StudentEndTestController extends AbstractController {

    private QuestionService questionService;

    @Override
    public void init() {
        questionService = getServiceFactory().getService(QUESTION_SERVICE, QuestionService.class);
    }

    @Override
    public String execute(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        @SuppressWarnings("unchecked")
        final List<TestQuestion> test = (List<TestQuestion>) request.getSession().getAttribute(SESSION_ATTRIBUTES.TEST);
        final Byte mark = questionService.endTest(test);
        request.getSession().removeAttribute(SESSION_ATTRIBUTES.TEST);
        request.getSession().setAttribute(SESSION_ATTRIBUTES.MARK, mark);
        response.sendRedirect(LINKS.STUDENT_RESULT);
        return REDIRECT;
    }
}
