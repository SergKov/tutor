package com.getprepared.controller.student;

import com.getprepared.controller.dto.TestQuestion;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.getprepared.constant.PageConstants.*;
import static com.getprepared.constant.WebConstants.REQUEST_ATTRIBUTES;
import static com.getprepared.constant.WebConstants.REQUEST_ATTRIBUTES.TITLE;
import static com.getprepared.constant.WebConstants.SESSION_ATTRIBUTES;

/**
 * Created by koval on 30.01.2017.
 */
public class StudentChangeQuestionController extends AbstractTestController {

    @Override
    public String execute(final HttpServletRequest request, final HttpServletResponse response) throws IOException {

        request.setAttribute(TITLE, getMessages().getMessage(NAMES.TEST, request.getLocale()));

        if (request.getSession().getAttribute(SESSION_ATTRIBUTES.TEST) == null) {
            response.sendRedirect(LINKS.NOT_FOUND);
            return REDIRECT;
        }

        @SuppressWarnings("unchecked")
        final List<TestQuestion> test = (List<TestQuestion>) request.getSession().getAttribute(SESSION_ATTRIBUTES.TEST);

        final Integer questionNumber = getQuestionNumber(request, test);
        request.setAttribute(REQUEST_ATTRIBUTES.TEST_QUESTION, test.get(questionNumber - 1));
        request.setAttribute(REQUEST_ATTRIBUTES.CURRENT_QUESTION, questionNumber);

        return PAGES.STUDENT_TEST;
    }
}
