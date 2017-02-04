package com.getprepared.controller.tutor;

import com.getprepared.controller.AbstractController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.getprepared.constant.PageConstants.LINKS;
import static com.getprepared.constant.PageConstants.REDIRECT;
import static com.getprepared.constant.WebConstants.SESSION_ATTRIBUTES;

/**
 * Created by koval on 04.02.2017.
 */
public class QuizChangeController extends AbstractController {

    @Override
    public void init() { }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().removeAttribute(SESSION_ATTRIBUTES.QUIZ_ID);
        response.sendRedirect(LINKS.TUTOR_QUIZZES);
        return REDIRECT;
    }
}
