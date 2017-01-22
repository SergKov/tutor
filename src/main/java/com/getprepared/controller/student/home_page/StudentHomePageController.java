package com.getprepared.controller.student.home_page;

import com.getprepared.exception.ParseException;
import com.getprepared.service.QuizService;
import com.getprepared.utils.Parser;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.getprepared.constant.PageConstants.*;
import static com.getprepared.constant.ServerConstants.SERVICES.QUIZ_SERVICE;
import static com.getprepared.constant.UtilsConstant.PARSER;
import static com.getprepared.constant.WebConstants.INPUTS;
import static com.getprepared.constant.WebConstants.REQUEST_ATTRIBUTES.ERROR_MSG;
import static com.getprepared.constant.WebConstants.SESSION_ATTRIBUTES;

/**
 * Created by koval on 19.01.2017.
 */
public class StudentHomePageController extends AbstractStudentHomePageController {

    private static final Logger LOG = Logger.getLogger(StudentHomePageController.class);

    private QuizService quizService;
    private Parser parser;

    @Override
    public void init() {
        quizService = getServiceFactory().getService(QUIZ_SERVICE, QuizService.class);
        parser = getUtilsFactory().getUtil(PARSER, Parser.class);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        final String quizId = request.getParameter(INPUTS.QUIZ);

        try {
            final Long id = parser.parseLong(quizId);
            final HttpSession httpSession = request.getSession();
            httpSession.setAttribute(SESSION_ATTRIBUTES.CHOSEN_QUIZ_ID, id);
            response.sendRedirect(LINKS.TEST);
            return REDIRECT;
        } catch (final ParseException e) {
            request.setAttribute(ERROR_MSG, getMessages().getMessage(ERRORS.INVALIDATED_ID, request.getLocale()));
            LOG.warn(e.getMessage(), e);
        }

        fillPage(request, quizService);

        return PAGES.STUDENT_HOME_PAGE;
    }
}
