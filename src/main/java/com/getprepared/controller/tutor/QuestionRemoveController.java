package com.getprepared.controller.tutor;

import com.getprepared.domain.Question;
import com.getprepared.exception.EntityNotFoundException;
import com.getprepared.exception.ParseException;
import com.getprepared.exception.ValidationException;
import com.getprepared.service.QuestionService;
import com.getprepared.service.QuizService;
import com.getprepared.utils.Parser;
import com.getprepared.utils.Validation;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.getprepared.constant.PageConstants.ERRORS;
import static com.getprepared.constant.PageConstants.PAGES;
import static com.getprepared.constant.ServerConstants.SERVICES.QUESTION_SERVICE;
import static com.getprepared.constant.ServerConstants.SERVICES.QUIZ_SERVICE;
import static com.getprepared.constant.UtilsConstant.PARSER;
import static com.getprepared.constant.UtilsConstant.VALIDATION;
import static com.getprepared.constant.WebConstants.INPUTS;
import static com.getprepared.constant.WebConstants.REQUEST_ATTRIBUTES.ERROR_MSG;

/**
 * Created by koval on 24.01.2017.
 */
public class QuestionRemoveController extends AbstractQuestionController {

    private static final Logger LOG = Logger.getLogger(QuestionRemoveController.class);

    private QuizService quizService;
    private QuestionService questionService;
    private Validation validation;
    private Parser parser;

    @Override
    public void init() {
        quizService = getServiceFactory().getService(QUIZ_SERVICE, QuizService.class);
        questionService = getServiceFactory().getService(QUESTION_SERVICE, QuestionService.class);
        validation = getUtilsFactory().getUtil(VALIDATION, Validation.class);
        parser = getUtilsFactory().getUtil(PARSER, Parser.class);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            final Long questionId = parser.parseLong(request.getParameter(INPUTS.QUESTION_ID));
            validation.validateId(questionId);
            final Question question = questionService.findById(questionId);
            validation.validateQuestion(question);
            questionService.remove(question);
        } catch (ValidationException | ParseException e) {
            request.setAttribute(ERROR_MSG, getMessages().getMessage(ERRORS.INVALIDATED_ID, request.getLocale()));
            LOG.warn(e.getMessage(), e);
        } catch (final EntityNotFoundException e) {
            request.setAttribute(ERROR_MSG, getMessages().getMessage(ERRORS.QUIZ_NOT_FOUND, request.getLocale()));
            LOG.warn(e.getMessage(), e);
        }

        fillPage(request, quizService, questionService);
        return PAGES.QUESTIONS;
    }
}
