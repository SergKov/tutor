package com.getprepared.controller.tutor;

import com.getprepared.domain.Answer;
import com.getprepared.domain.Question;
import com.getprepared.exception.EntityNotFoundException;
import com.getprepared.exception.ValidationException;
import com.getprepared.service.AnswerService;
import com.getprepared.service.QuestionService;
import com.getprepared.service.QuizService;
import com.getprepared.utils.Validation;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.getprepared.constant.PageConstants.*;
import static com.getprepared.constant.ServerConstants.SERVICES.*;
import static com.getprepared.constant.UtilsConstant.VALIDATION;
import static com.getprepared.constant.WebConstants.INPUTS;
import static com.getprepared.constant.WebConstants.REQUEST_ATTRIBUTES;
import static com.getprepared.constant.WebConstants.REQUEST_ATTRIBUTES.ERROR_MSG;
import static com.getprepared.constant.WebConstants.REQUEST_ATTRIBUTES.TITLE;

/**
 * Created by koval on 26.01.2017.
 */
public class QuestionPageController extends AbstractQuestionsController {

    private static final Logger LOG = Logger.getLogger(QuestionPageController.class);

    private QuizService quizService;
    private QuestionService questionService;
    private AnswerService answerService;
    private Validation validation;

    @Override
    public void init() {
        quizService = getServiceFactory().getService(QUIZ_SERVICE, QuizService.class);
        questionService = getServiceFactory().getService(QUESTION_SERVICE, QuestionService.class);
        answerService = getServiceFactory().getService(ANSWER_SERVICE, AnswerService.class);
        validation = getUtilsFactory().getUtil(VALIDATION, Validation.class);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        request.setAttribute(TITLE, getMessages().getMessage(NAMES.QUESTION, request.getLocale()));

        final String questionId = request.getParameter(INPUTS.QUESTION_ID);

        try {
            final Long parsedQuestionId = Long.parseLong(questionId);
            validation.validateId(parsedQuestionId);
            final Question question = questionService.findById(parsedQuestionId);
            final List<Answer> answers = answerService.findByQuestionId(parsedQuestionId);
            question.setAnswers(answers);
            request.setAttribute(REQUEST_ATTRIBUTES.QUESTION, question);
        } catch (final EntityNotFoundException e) {
            request.setAttribute(ERROR_MSG, getMessages().getMessage(ERRORS.QUESTION_NOT_FOUND, request.getLocale()));
            LOG.warn(e.getMessage(), e);
        } catch (final ValidationException e) {
            request.setAttribute(ERROR_MSG, getMessages().getMessage(ERRORS.INVALIDATED_ID, request.getLocale()));
            LOG.warn(e.getMessage(), e);
        } catch (final NumberFormatException e) {
            response.sendRedirect(PAGES.NOT_FOUND);
            return REDIRECT;
        }

        fillPage(request, quizService, questionService);
        return PAGES.QUESTION;
    }
}

