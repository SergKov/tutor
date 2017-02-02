package com.getprepared.controller.tutor;

import com.getprepared.domain.Question;
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

import static com.getprepared.constant.PageConstants.*;
import static com.getprepared.constant.ServerConstants.SERVICES.QUESTION_SERVICE;
import static com.getprepared.constant.ServerConstants.SERVICES.QUIZ_SERVICE;
import static com.getprepared.constant.UtilsConstant.VALIDATION;
import static com.getprepared.constant.WebConstants.INPUTS;
import static com.getprepared.constant.WebConstants.REQUEST_ATTRIBUTES.CONFIRM_MSG;
import static com.getprepared.constant.WebConstants.REQUEST_ATTRIBUTES.QUESTION;
import static com.getprepared.constant.WebConstants.REQUEST_ATTRIBUTES.TITLE;

/**
 * Created by koval on 25.01.2017.
 */
public class QuestionsPageController extends AbstractQuestionsController {

    private static final Logger LOG = Logger.getLogger(QuestionsPageController.class);

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

        request.setAttribute(CONFIRM_MSG, getMessages().getMessage(CONFIRMS.ARE_YOU_SURE, request.getLocale())); //TODO

        final String questionIdString = request.getParameter(INPUTS.QUESTION_ID);

        if (StringUtils.isNumeric(questionIdString)) {
            try {
                request.setAttribute(TITLE, getMessages().getMessage(NAMES.QUESTION, request.getLocale()));

                final Long questionId = Long.valueOf(questionIdString);
                validation.validateId(questionId);
                final Question question = questionService.findById(questionId);
                request.setAttribute(QUESTION, question);
            } catch (NumberFormatException | ValidationException | EntityNotFoundException e) {
                response.sendRedirect(LINKS.NOT_FOUND);
                LOG.warn(e.getMessage(), e);
                return REDIRECT;
            }
            return PAGES.TUTOR_QUESTION;

        } else {

            try {
                fillPage(request, quizService, questionService, validation);
            } catch (final ValidationException e) {
                LOG.warn(e.getMessage(), e);
                response.sendRedirect(LINKS.NOT_FOUND);
                return REDIRECT;
            }

            return PAGES.TUTOR_QUESTIONS;
        }
    }
}
