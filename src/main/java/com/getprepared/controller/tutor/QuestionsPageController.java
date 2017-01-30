package com.getprepared.controller.tutor;

import com.getprepared.constant.PageConstants;
import com.getprepared.constant.WebConstants;
import com.getprepared.domain.Answer;
import com.getprepared.domain.Question;
import com.getprepared.exception.EntityNotFoundException;
import com.getprepared.exception.ValidationException;
import com.getprepared.service.AnswerService;
import com.getprepared.service.QuestionService;
import com.getprepared.service.QuizService;
import com.getprepared.utils.Validation;
import com.getprepared.utils.impl.Messages;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.getprepared.constant.PageConstants.*;
import static com.getprepared.constant.PageConstants.NAMES;
import static com.getprepared.constant.PageConstants.PAGES;
import static com.getprepared.constant.ServerConstants.SERVICES.ANSWER_SERVICE;
import static com.getprepared.constant.ServerConstants.SERVICES.QUESTION_SERVICE;
import static com.getprepared.constant.ServerConstants.SERVICES.QUIZ_SERVICE;
import static com.getprepared.constant.UtilsConstant.VALIDATION;
import static com.getprepared.constant.WebConstants.*;
import static com.getprepared.constant.WebConstants.REQUEST_ATTRIBUTES.*;

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

        final String questionIdString = request.getParameter(INPUTS.QUESTION_ID);

        if (StringUtils.isNumericSpace(questionIdString)) {
            try {
                request.setAttribute(TITLE, getMessages().getMessage(NAMES.QUESTION, request.getLocale()));

                final Long questionId = Long.valueOf(questionIdString);
                validation.validateId(questionId);
                final Question question = questionService.findById(questionId);
                request.setAttribute(QUESTION, question);
            } catch (final NumberFormatException | ValidationException | EntityNotFoundException e) {
                response.sendRedirect(LINKS.NOT_FOUND);
                LOG.warn(e.getMessage(), e);
                return REDIRECT;
            }
            return PAGES.QUESTION;
        } else {
            fillPage(request, quizService, questionService);
            return PAGES.QUESTIONS;
        }
    }
}
