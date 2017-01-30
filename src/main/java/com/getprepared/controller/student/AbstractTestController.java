package com.getprepared.controller.student;

import com.getprepared.constant.PageConstants;
import com.getprepared.constant.PageConstants.NAMES;
import com.getprepared.constant.WebConstants;
import com.getprepared.controller.AbstractController;
import com.getprepared.controller.Controller;
import com.getprepared.domain.Answer;
import com.getprepared.domain.Question;
import com.getprepared.domain.Quiz;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.getprepared.constant.WebConstants.*;
import static com.getprepared.constant.WebConstants.REQUEST_ATTRIBUTES.QUESTION;
import static com.getprepared.constant.WebConstants.REQUEST_ATTRIBUTES.QUESTIONS_LENGTH;
import static com.getprepared.constant.WebConstants.REQUEST_ATTRIBUTES.TITLE;

/**
 * Created by koval on 30.01.2017.
 */
public abstract class AbstractTestController extends AbstractController {

    private static final Logger LOG = Logger.getLogger(AbstractTestController.class);

    public void fillPage(final HttpServletRequest request, final HttpServletResponse response) throws IOException {

        request.setAttribute(TITLE, getMessages().getMessage(NAMES.TEST, request.getLocale()));

        final Object quizObject = request.getSession().getAttribute(SESSION_ATTRIBUTES.QUIZ);
        final Quiz quiz = (Quiz) quizObject;

        final List<Question> questions = quiz.getQuestions();
        request.setAttribute(QUESTIONS_LENGTH, questions.size());

        try {
            final Integer questionNumber = Integer.valueOf(request.getParameter(REQUEST_ATTRIBUTES.QUESTION_NUMBER));
            final Question firstQuestion = questions.get(questionNumber);
            request.setAttribute(QUESTION, firstQuestion.getText());
        } catch (final NumberFormatException e) {
            response.sendRedirect(PageConstants.PAGES.NOT_FOUND);
            LOG.warn(e.getMessage(), e);
        }
    }

}
