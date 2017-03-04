package com.getprepared.controller.tutor;

import com.getprepared.annotation.Bean;
import com.getprepared.annotation.Inject;
import com.getprepared.domain.Answer;
import com.getprepared.domain.AnswerType;
import com.getprepared.domain.Question;
import com.getprepared.domain.Quiz;
import com.getprepared.exception.EntityExistsException;
import com.getprepared.exception.EntityNotFoundException;
import com.getprepared.exception.ValidationException;
import com.getprepared.service.QuestionService;
import com.getprepared.service.QuizService;
import com.getprepared.util.Validation;
import com.getprepared.util.impl.Messages;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.getprepared.constant.PageConstants.*;
import static com.getprepared.constant.WebConstants.INPUTS;
import static com.getprepared.constant.WebConstants.REQUEST_ATTRIBUTES;
import static com.getprepared.constant.WebConstants.REQUEST_ATTRIBUTES.ERROR_MSG;

/**
 * Created by koval on 26.01.2017.
 */
@Bean("questionAdd")
public class QuestionAddController extends AbstractQuestionAddController {

    private static final Logger LOG = Logger.getLogger(QuestionAddController.class);

    @Inject
    private QuizService quizService;

    @Inject
    private QuestionService questionService;

    @Inject
    private Validation validation;

    @Inject
    private Messages messages;

    @Override
    public String execute(final HttpServletRequest request, final HttpServletResponse response) throws IOException {

        final Long quizId = (Long) request.getSession().getAttribute(INPUTS.QUIZ_ID);

        final Question question = new Question();

        String[] answersText = null;
        String[] answersType = null;
        try {
            final Quiz quiz = quizService.findById(quizId);
            question.setQuiz(quiz);

            final String questionText = request.getParameter(INPUTS.QUESTION_TEXT);
            question.setText(questionText);

            request.setAttribute(REQUEST_ATTRIBUTES.QUESTION_TEXT, questionText);

            answersText = request.getParameterValues(INPUTS.ANSWER_TEXT);
            answersType = request.getParameterValues(INPUTS.ANSWER_TYPE);

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
                
                response.sendRedirect(LINKS.TUTOR_QUESTIONS);
                return REDIRECT;
            }
        } catch (final EntityNotFoundException e) {
            LOG.warn(e.getMessage(), e);
            response.sendRedirect(LINKS.NOT_FOUND);
            return REDIRECT;
        } catch (ValidationException | IllegalArgumentException e) {
            LOG.warn(e.getMessage(), e);
            request.setAttribute(ERROR_MSG, messages.getMessage(ERRORS.INVALIDATED_ANSWERS, request.getLocale()));
        } catch (final EntityExistsException e) {
            LOG.warn(e.getMessage(), e);
            request.setAttribute(ERROR_MSG, messages.getMessage(ERRORS.QUESTION_EXISTS, request.getLocale()));
        }

        request.setAttribute(REQUEST_ATTRIBUTES.ANSWER_TYPE, answersType);
        request.setAttribute(REQUEST_ATTRIBUTES.ANSWER_TEXT, answersText);

        return fillPage(request, response, validation);
    }
}
