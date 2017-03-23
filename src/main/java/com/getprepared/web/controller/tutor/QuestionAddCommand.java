package com.getprepared.web.controller.tutor;

import com.getprepared.annotation.Inject;
import com.getprepared.core.converter.Converter;
import com.getprepared.core.exception.EntityExistsException;
import com.getprepared.core.exception.EntityNotFoundException;
import com.getprepared.core.service.QuestionService;
import com.getprepared.core.service.QuizService;
import com.getprepared.core.util.Messages;
import com.getprepared.persistence.domain.Answer;
import com.getprepared.persistence.domain.Question;
import com.getprepared.persistence.domain.Quiz;
import com.getprepared.persistence.domain.Type;
import com.getprepared.web.annotation.Controller;
import com.getprepared.web.form.QuestionAddForm;
import com.getprepared.web.validation.ValidationService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.getprepared.web.constant.PageConstants.*;
import static com.getprepared.web.constant.WebConstants.INPUTS;
import static com.getprepared.web.constant.WebConstants.REQUEST_ATTRIBUTES;
import static com.getprepared.web.constant.WebConstants.REQUEST_ATTRIBUTES.ERROR_MSG;
import static com.getprepared.web.constant.WebConstants.REQUEST_ATTRIBUTES.ERROR_MSGS;
import static org.apache.commons.collections4.MapUtils.*;

/**
 * Created by koval on 26.01.2017.
 */
@Controller
public class QuestionAddCommand extends AbstractQuestionAddCommand {

    private static final Logger LOG = Logger.getLogger(QuestionAddCommand.class);

    @Inject
    private QuizService quizService;

    @Inject
    private QuestionService questionService;

    @Inject
    private ValidationService validationService;

    @Inject
    private Converter<QuestionAddForm, Question> questionConverter;

    @Inject
    private Messages messages;

    @Override
    public String execute(final HttpServletRequest request, final HttpServletResponse response) throws IOException {

        final Long quizId = (Long) request.getSession().getAttribute(INPUTS.QUIZ_ID);

        final QuestionAddForm questionAddForm = new QuestionAddForm();

        String[] answersText = null;
        String[] answersType = null;
        try {
            final Quiz quiz = quizService.findById(quizId);

            final String questionText = request.getParameter(INPUTS.QUESTION_TEXT);
            questionAddForm.setQuestion(questionText);

            request.setAttribute(REQUEST_ATTRIBUTES.QUESTION_TEXT, questionText);

            answersText = request.getParameterValues(INPUTS.ANSWER_TEXT);
            answersType = request.getParameterValues(INPUTS.ANSWER_TYPE);

            if (answersText.length != answersType.length) {
                request.setAttribute(ERROR_MSG, ERRORS.FILL_NOT_ALL_FIELDS);
            } else {
                questionAddForm.setAnswers(answersText);
                questionAddForm.setAnswerType(answersType);

                final Map<String, String> errors = validationService.validate(questionAddForm);
                if (isNotEmpty(errors)) {
                    request.setAttribute(ERROR_MSGS, errors);
                } else {
                    final Question question = questionConverter.convert(questionAddForm);
                    question.setQuiz(quiz);

                    questionService.save(question);

                    response.sendRedirect(LINKS.TUTOR_QUESTIONS);
                    return REDIRECT;
                }
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
        request.setAttribute(REQUEST_ATTRIBUTES.ANSWER_TYPE, answersType); // TODO
        request.setAttribute(REQUEST_ATTRIBUTES.ANSWER_TEXT, answersText);

        return returnPage(request);
    }
}
