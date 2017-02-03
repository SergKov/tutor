package com.getprepared.controller.tutor;

import com.getprepared.controller.common.AbstractQuizController;
import com.getprepared.domain.Quiz;
import com.getprepared.exception.EntityNotFoundException;
import com.getprepared.exception.ValidationException;
import com.getprepared.service.QuizService;
import com.getprepared.utils.Validation;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.getprepared.constant.PageConstants.*;
import static com.getprepared.constant.ServerConstants.SERVICES.QUIZ_SERVICE;
import static com.getprepared.constant.UtilsConstant.VALIDATION;
import static com.getprepared.constant.WebConstants.INPUTS;
import static com.getprepared.constant.WebConstants.REQUEST_ATTRIBUTES.ERROR_MSG;

/**
 * Created by koval on 24.01.2017.
 */
public class QuizRemoveController extends AbstractQuizController {

    private static final Logger LOG = Logger.getLogger(QuizRemoveController.class);

    private QuizService quizService;
    private Validation validation;

    @Override
    public void init() {
        quizService = getServiceFactory().getService(QUIZ_SERVICE, QuizService.class);
        validation = getUtilsFactory().getUtil(VALIDATION, Validation.class);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        final String quizIdString = request.getParameter(INPUTS.QUIZ_ID);

        try {
            final Long quizId = Long.parseLong(quizIdString);
            validation.validateId(quizId);
            final Quiz quiz = quizService.findById(quizId);
            validation.validateQuiz(quiz);
            quizService.remove(quiz);
            response.sendRedirect(LINKS.TUTOR_QUIZZES);
            return REDIRECT;
        } catch (final EntityNotFoundException e) {
            LOG.warn(e.getMessage(), e);
            request.setAttribute(ERROR_MSG, getMessages().getMessage(ERRORS.QUESTION_NOT_FOUND, request.getLocale()));
        } catch (ValidationException | NumberFormatException e) {
            LOG.warn(e.getMessage(), e);
            response.sendRedirect(LINKS.NOT_FOUND);
            return REDIRECT;
        }

        fillPage(request, quizService);
        return PAGES.TUTOR_QUIZZES;
    }
}
