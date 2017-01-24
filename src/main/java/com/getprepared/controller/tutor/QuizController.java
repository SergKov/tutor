package com.getprepared.controller.tutor;

import com.getprepared.controller.common.AbstractQuizController;
import com.getprepared.exception.ParseException;
import com.getprepared.exception.ValidationException;
import com.getprepared.service.QuizService;
import com.getprepared.utils.Parser;
import com.getprepared.utils.Validation;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.getprepared.constant.PageConstants.*;
import static com.getprepared.constant.ServerConstants.SERVICES.QUIZ_SERVICE;
import static com.getprepared.constant.UtilsConstant.PARSER;
import static com.getprepared.constant.UtilsConstant.VALIDATION;
import static com.getprepared.constant.WebConstants.INPUTS;
import static com.getprepared.constant.WebConstants.REQUEST_ATTRIBUTES.ERROR_MSG;
import static com.getprepared.constant.WebConstants.SESSION_ATTRIBUTES;

/**
 * Created by koval on 21.01.2017.
 */
public class QuizController extends AbstractQuizController {

    private static final Logger LOG = Logger.getLogger(QuizController.class);

    private QuizService quizService;
    private Validation validation;
    private Parser parser;

    @Override
    public void init() {
        quizService = getServiceFactory().getService(QUIZ_SERVICE, QuizService.class);
        validation = getUtilsFactory().getUtil(VALIDATION, Validation.class);
        parser = getUtilsFactory().getUtil(PARSER, Parser.class);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        final String quizId = request.getParameter(INPUTS.QUIZ);

        try {
            final Long parsedQuizId = parser.parseLong(quizId);
            validation.validateId(parsedQuizId);
            request.getSession().setAttribute(SESSION_ATTRIBUTES.CHOSEN_QUIZ_ID, parsedQuizId);
            response.sendRedirect(LINKS.TUTOR_QUESTIONS);
            return REDIRECT;
        } catch (final ParseException e) {
            request.setAttribute(ERROR_MSG, getMessages().getMessage(ERRORS.INCORRECT_ID, request.getLocale()));
            LOG.warn(e.getMessage(), e);
        } catch (final ValidationException e) {
            request.setAttribute(ERROR_MSG, getMessages().getMessage(ERRORS.INVALIDATED_ID, request.getLocale()));
            LOG.warn(e.getMessage(), e);
        }

        fillPage(request, quizService);

        return PAGES.TUTOR_QUIZZES;
    }
}
