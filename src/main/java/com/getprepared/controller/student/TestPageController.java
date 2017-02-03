package com.getprepared.controller.student;

import com.getprepared.domain.Quiz;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.getprepared.constant.PageConstants.*;
import static com.getprepared.constant.WebConstants.REQUEST_ATTRIBUTES.QUIZ;
import static com.getprepared.constant.WebConstants.SESSION_ATTRIBUTES;

/**
 * Created by koval on 30.01.2017.
 */
public class TestPageController extends AbstractTestController {

    private static final Logger LOG = Logger.getLogger(TestPageController.class);

    @Override
    public void init() {
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        fillPage(request);

        final Object quizObject = request.getSession().getAttribute(SESSION_ATTRIBUTES.QUIZ);
        if (quizObject == null) {
            response.sendRedirect(LINKS.NOT_FOUND);
            return REDIRECT;
        }

        final Quiz quiz = (Quiz) quizObject;
        request.setAttribute(QUIZ, quiz);
        return PAGES.STUDENT_TEST;
    }
}
