package com.getprepared.controller.tutor;

import com.getprepared.annotation.Bean;
import com.getprepared.annotation.Inject;
import com.getprepared.domain.Question;
import com.getprepared.exception.EntityNotFoundException;
import com.getprepared.exception.ValidationException;
import com.getprepared.service.QuestionService;
import com.getprepared.service.QuizService;
import com.getprepared.util.Validation;
import com.getprepared.util.impl.Messages;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.getprepared.constant.PageConstants.*;
import static com.getprepared.constant.WebConstants.INPUTS;
import static com.getprepared.constant.WebConstants.REQUEST_ATTRIBUTES.QUESTION;
import static com.getprepared.constant.WebConstants.REQUEST_ATTRIBUTES.TITLE;

/**
 * Created by koval on 25.01.2017.
 */
@Bean("questionsPage")
public class QuestionsPageController extends AbstractQuestionsController {

    private static final Logger LOG = Logger.getLogger(QuestionsPageController.class);

    @Inject
    private QuizService quizService;

    @Inject
    private QuestionService questionService;

    @Inject
    private Validation validation;

    @Inject
    private Messages messages;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        final String questionIdString = request.getParameter(INPUTS.QUESTION_ID);

        if (StringUtils.isNumeric(questionIdString)) {
            try {
                request.setAttribute(TITLE, messages.getMessage(NAMES.QUESTION, request.getLocale()));

                final Long questionId = Long.valueOf(questionIdString);
                validation.validateId(questionId);
                
                final Question question = questionService.findById(questionId);
                request.setAttribute(QUESTION, question);
            } catch (NumberFormatException | ValidationException | EntityNotFoundException e) {
                LOG.warn(e.getMessage(), e);
                response.sendRedirect(LINKS.NOT_FOUND);
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
