package com.getprepared.controller.tutor;

import com.getprepared.constant.WebConstants;
import com.getprepared.domain.Answer;
import com.getprepared.domain.AnswerType;
import com.getprepared.domain.Question;
import com.getprepared.domain.Quiz;
import com.getprepared.exception.EntityExistsException;
import com.getprepared.exception.EntityNotFoundException;
import com.getprepared.exception.ValidationException;
import com.getprepared.service.QuestionService;
import com.getprepared.service.QuizService;
import com.getprepared.utils.Validation;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.getprepared.constant.PageConstants.*;
import static com.getprepared.constant.ServerConstants.SERVICES.QUESTION_SERVICE;
import static com.getprepared.constant.ServerConstants.SERVICES.QUIZ_SERVICE;
import static com.getprepared.constant.UtilsConstant.VALIDATION;
import static com.getprepared.constant.WebConstants.*;
import static com.getprepared.constant.WebConstants.INPUTS;
import static com.getprepared.constant.WebConstants.REQUEST_ATTRIBUTES.ERROR_MSG;
import static com.getprepared.constant.WebConstants.REQUEST_ATTRIBUTES.TITLE;

/**
 * Created by koval on 26.01.2017.
 */
public class QuestionAddController extends AbstractQuestionAddController {

    private static final Logger LOG = Logger.getLogger(QuestionAddController.class);

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

        request.setAttribute(TITLE, getMessages().getMessage(NAMES.ADD_QUESTION, request.getLocale()));

        final Object quizIdObject = request.getSession().getAttribute(INPUTS.QUIZ_ID);

        try {
            final Long quizId = (Long) (quizIdObject);

            final Question question = new Question();
            final Quiz quiz = quizService.findById(quizId);
            question.setQuiz(quiz);
            final String questionText = request.getParameter(INPUTS.QUESTION_TEXT);
            question.setText(questionText);

            request.setAttribute(REQUEST_ATTRIBUTES.QUESTION_TEXT, questionText);

            final String[] answersText = request.getParameterValues(INPUTS.ANSWER_TEXT);
            final String[] answersType = request.getParameterValues(INPUTS.ANSWER_TYPE);

            if (answersText.length != answersType.length) {
                request.setAttribute(ERROR_MSG, ERRORS.FILL_NOT_ALL_FIELDS);
            } else {
                final List<Answer> answers = new ArrayList<>();

                for (int i = 0; i < answersText.length; i++) {
                    final Answer answer = new Answer();
                    answer.setText(answersText[i]);
                    answer.setType(AnswerType.valueOf(answersType[i]));
                    answer.setQuestion(question);
                    validation.validateAnswer(answer);
                    answers.add(answer);
                }

                question.setAnswers(answers);
                validation.validateQuestion(question);
                questionService.save(question);
                response.sendRedirect(LINKS.QUESTIONS);
                return REDIRECT;
            }
        } catch (final EntityNotFoundException e) {
            LOG.warn(e.getMessage(), e);
            response.sendRedirect(LINKS.NOT_FOUND);
            return REDIRECT;
        } catch (ValidationException | IllegalArgumentException e) {
            request.setAttribute(ERROR_MSG, getMessages().getMessage(ERRORS.INVALIDATED_ANSWERS, request.getLocale()));
            LOG.warn(e.getMessage(), e);
        } catch (final EntityExistsException e) {
            request.setAttribute(ERROR_MSG, getMessages().getMessage(ERRORS.QUESTION_EXISTS, request.getLocale()));
            LOG.warn(e.getMessage(), e);
        }

        try {
            fillPage(request, validation);
        } catch (final ValidationException e) {
            LOG.warn(e.getMessage(), e);
            response.sendRedirect(LINKS.NOT_FOUND);
        }

        return PAGES.TUTOR_ADD_QUESTION;
    }
}
