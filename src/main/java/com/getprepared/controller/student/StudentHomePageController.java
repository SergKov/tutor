package com.getprepared.controller.student;

import com.getprepared.service.QuizService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.getprepared.constant.PageConstants.*;
import static com.getprepared.constant.ServerConstants.SERVICES.QUIZ_SERVICE;
import static com.getprepared.constant.WebConstants.INPUTS;
import static com.getprepared.constant.WebConstants.SESSION_ATTRIBUTES;

/**
 * Created by koval on 19.01.2017.
 */
public class StudentHomePageController extends AbstractStudentHomePageController {

    private static final Logger LOG = Logger.getLogger(StudentHomePageController.class);

    private QuizService quizService;

    @Override
    public void init() {
        quizService = getServiceFactory().getService(QUIZ_SERVICE, QuizService.class);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        final String quizId = request.getParameter(INPUTS.QUIZ);

        try {
            final Long id = Long.parseLong(quizId);
            final HttpSession httpSession = request.getSession();
            httpSession.setAttribute(SESSION_ATTRIBUTES.QUIZ_ID, id);
            response.sendRedirect(LINKS.TEST);
        } catch (final NumberFormatException e) {
            LOG.warn(e.getMessage(), e);
            response.sendRedirect(PAGES.NOT_FOUND);
        }

        return REDIRECT;
    }
}
