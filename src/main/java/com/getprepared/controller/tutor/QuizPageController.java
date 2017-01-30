package com.getprepared.controller.tutor;

import com.getprepared.controller.common.AbstractQuizController;
import com.getprepared.domain.Question;
import com.getprepared.domain.Quiz;
import com.getprepared.exception.EntityNotFoundException;
import com.getprepared.exception.ValidationException;
import com.getprepared.service.QuestionService;
import com.getprepared.service.QuizService;
import com.getprepared.utils.Validation;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.getprepared.constant.PageConstants.*;
import static com.getprepared.constant.ServerConstants.SERVICES.QUESTION_SERVICE;
import static com.getprepared.constant.ServerConstants.SERVICES.QUIZ_SERVICE;
import static com.getprepared.constant.UtilsConstant.VALIDATION;
import static com.getprepared.constant.WebConstants.INPUTS;
import static com.getprepared.constant.WebConstants.REQUEST_ATTRIBUTES.QUESTIONS;
import static com.getprepared.constant.WebConstants.REQUEST_ATTRIBUTES.TITLE;

/**
 * Created by koval on 22.01.2017.
 */
public class QuizPageController extends AbstractQuizController {

    private static final Logger LOG = Logger.getLogger(QuizPageController.class);

    private QuizService quizService;
    private QuestionService questionService;
    private Validation validation;

    @Override
    public void init() {
        quizService = getServiceFactory().getService(QUIZ_SERVICE, QuizService.class);
        questionService = getServiceFactory().getService(QUESTION_SERVICE, QuestionService.class);
        validation = getUtilsFactory().getUtil(VALIDATION, Validation.class);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        final String quizIdString = request.getParameter(INPUTS.QUIZ_ID);

        if (StringUtils.isNumericSpace(quizIdString)) {
            try {
                request.setAttribute(TITLE, getMessages().getMessage(NAMES.QUESTIONS, request.getLocale()));

                final Long quizId = Long.valueOf(quizIdString);
                validation.validateId(quizId);
                final List<Question> questions = questionService.findByQuizId(quizId);
                request.setAttribute(QUESTIONS, questions);
            } catch (final ValidationException e) {
                response.sendRedirect(PAGES.NOT_FOUND);
                LOG.warn(e.getMessage(), e);
                return REDIRECT;
            }
            return PAGES.QUESTIONS;
        } else {
            fillPage(request, quizService);
            return PAGES.TUTOR_QUIZZES;
        }
    }
}
