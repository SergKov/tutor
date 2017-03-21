package com.getprepared.web.controller.tutor;

import com.getprepared.annotation.Component;
import com.getprepared.annotation.Inject;
import com.getprepared.core.exception.EntityExistsException;
import com.getprepared.core.exception.EntityNotFoundException;
import com.getprepared.core.service.QuestionService;
import com.getprepared.core.service.QuizService;
import com.getprepared.core.util.Messages;
import com.getprepared.persistence.domain.Answer;
import com.getprepared.persistence.domain.Question;
import com.getprepared.persistence.domain.Quiz;
import com.getprepared.persistence.domain.Type;
import com.getprepared.web.validation.ValidationService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.getprepared.web.constant.PageConstants.*;
import static com.getprepared.web.constant.WebConstants.INPUTS;
import static com.getprepared.web.constant.WebConstants.REQUEST_ATTRIBUTES;
import static com.getprepared.web.constant.WebConstants.REQUEST_ATTRIBUTES.ERROR_MSG;

/**
 * Created by koval on 26.01.2017.
 */
@Component("questionAdd")
public class QuestionAddController extends AbstractQuestionAddController {

    private static final Logger LOG = Logger.getLogger(QuestionAddController.class);

    @Inject
    private QuizService quizService;

    @Inject
    private QuestionService questionService;

    @Inject
    private ValidationService validationService;

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
                    answer.setType(Type.valueOf(answersType[i]));
                    answer.setQuestion(question);
                    // TODO add validation
                    answers.add(answer);
                }

                question.setAnswers(answers);
                // TODO add validation
                questionService.save(question);
                
                response.sendRedirect(LINKS.TUTOR_QUESTIONS);
                return REDIRECT;
            }
        } catch (final EntityNotFoundException e) {
            LOG.warn(e.getMessage(), e);
            response.sendRedirect(LINKS.NOT_FOUND);
            return REDIRECT;
        } catch (final IllegalArgumentException e) { // TODO ???
            LOG.warn(e.getMessage(), e);
            request.setAttribute(ERROR_MSG, messages.getMessage(ERRORS.INVALIDATED_ANSWERS, request.getLocale()));
        } catch (final EntityExistsException e) {
            LOG.warn(e.getMessage(), e);
            request.setAttribute(ERROR_MSG, messages.getMessage(ERRORS.QUESTION_EXISTS, request.getLocale()));
        }

        request.setAttribute(REQUEST_ATTRIBUTES.ANSWER_TYPE, answersType);
        request.setAttribute(REQUEST_ATTRIBUTES.ANSWER_TEXT, answersText);

        return returnPage(request);
    }
}
