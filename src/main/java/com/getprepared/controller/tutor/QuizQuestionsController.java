package com.getprepared.controller.tutor;

import com.getprepared.constant.PageConstants;
import com.getprepared.constant.WebConstants;
import com.getprepared.controller.common.*;
import com.getprepared.exception.ParseException;
import com.getprepared.exception.ValidationException;
import com.getprepared.service.QuizService;
import com.getprepared.utils.Parser;
import com.getprepared.utils.Validation;
import com.getprepared.utils.factory.UtilsFactory;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static com.getprepared.constant.PageConstants.*;
import static com.getprepared.constant.PageConstants.REDIRECT;
import static com.getprepared.constant.ServerConstants.SERVICES.QUIZ_SERVICE;
import static com.getprepared.constant.UtilsConstant.PARSER;
import static com.getprepared.constant.UtilsConstant.VALIDATION;
import static com.getprepared.constant.WebConstants.*;
import static com.getprepared.constant.WebConstants.INPUTS.*;
import static com.getprepared.constant.WebConstants.REQUEST_ATTRIBUTES.ERROR_MSG;

/**
 * Created by koval on 24.01.2017.
 */
public class QuizQuestionsController extends AbstractQuizController {

    private static final Logger LOG = Logger.getLogger(QuizQuestionsController.class);

    private Validation validation;
    private QuizService quizService;
    private Parser parser;

    @Override
    public void init() {
        validation = getUtilsFactory().getUtil(VALIDATION, Validation.class);
        quizService = getServiceFactory().getService(QUIZ_SERVICE, QuizService.class);
        parser = getUtilsFactory().getUtil(PARSER, Parser.class);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        final String quizIdString = request.getParameter(QUIZ_ID);

        try {
            final Long quizId = parser.parseLong(quizIdString);
            validation.validateId(quizId);
            request.getSession().setAttribute(SESSION_ATTRIBUTES.QUIZ_ID, quizId);
            response.sendRedirect(LINKS.TUTOR_QUESTIONS);
            return REDIRECT;
        } catch (ValidationException | ParseException e) {
            request.setAttribute(ERROR_MSG, getMessages().getMessage(ERRORS.INVALIDATED_ID, request.getLocale()));
            LOG.warn(e.getMessage(), e);
        }

        fillPage(request, quizService);
        return PAGES.TUTOR_QUIZZES;
    }
}
