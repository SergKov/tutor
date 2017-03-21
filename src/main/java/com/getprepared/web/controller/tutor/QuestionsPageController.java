package com.getprepared.web.controller.tutor;

import com.getprepared.annotation.Component;
import com.getprepared.annotation.Inject;
import com.getprepared.core.exception.EntityNotFoundException;
import com.getprepared.core.service.QuestionService;
import com.getprepared.core.service.QuizService;
import com.getprepared.core.util.Messages;
import com.getprepared.persistence.domain.Question;
import com.getprepared.web.validation.ValidationService;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.getprepared.web.constant.PageConstants.*;
import static com.getprepared.web.constant.WebConstants.INPUTS;
import static com.getprepared.web.constant.WebConstants.REQUEST_ATTRIBUTES.QUESTION;
import static com.getprepared.web.constant.WebConstants.REQUEST_ATTRIBUTES.TITLE;

/**
 * Created by koval on 25.01.2017.
 */
@Component("questionsPage")
public class QuestionsPageController extends AbstractQuestionsController {

    private static final Logger LOG = Logger.getLogger(QuestionsPageController.class);

    @Inject
    private QuizService quizService;

    @Inject
    private QuestionService questionService;

    @Inject
    private ValidationService validationService;

    @Inject
    private Messages messages;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        final String questionIdString = request.getParameter(INPUTS.QUESTION_ID);

        if (StringUtils.isNumeric(questionIdString)) {
            try {
                request.setAttribute(TITLE, messages.getMessage(NAMES.QUESTION, request.getLocale()));
                final Long questionId = Long.valueOf(questionIdString);
                final Question question = questionService.findById(questionId);
                request.setAttribute(QUESTION, question);
            } catch (NumberFormatException | EntityNotFoundException e) {
                LOG.warn(e.getMessage(), e);
                response.sendRedirect(LINKS.NOT_FOUND);
                return REDIRECT;
            }
            return PAGES.TUTOR_QUESTION;
        } else {
            try {
                fillPage(request, quizService, questionService);
            } catch (final EntityNotFoundException e) {
                LOG.warn(e.getMessage(), e);
                response.sendRedirect(LINKS.NOT_FOUND);
                return REDIRECT;
            }

            return PAGES.TUTOR_QUESTIONS;
        }
    }
}
