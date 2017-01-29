package com.getprepared.controller.student;

import com.getprepared.constant.WebConstants;
import com.getprepared.controller.AbstractController;
import com.getprepared.domain.Answer;
import com.getprepared.domain.Question;
import com.getprepared.domain.Quiz;
import com.getprepared.service.AnswerService;
import com.getprepared.service.QuestionService;
import com.getprepared.service.QuizService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import static com.getprepared.constant.PageConstants.PAGES;
import static com.getprepared.constant.ServerConstants.SERVICES.*;
import static com.getprepared.constant.WebConstants.*;
import static com.getprepared.constant.WebConstants.REQUEST_ATTRIBUTES.QUESTION;
import static com.getprepared.constant.WebConstants.REQUEST_ATTRIBUTES.QUESTIONS_LENGTH;
import static com.getprepared.constant.WebConstants.SESSION_ATTRIBUTES;

/**
 * Created by koval on 30.01.2017.
 */
public class TestPageController extends AbstractController {

    private static final Logger LOG = Logger.getLogger(TestPageController.class);

    private QuizService quizService;
    private QuestionService questionService;
    private AnswerService answerService;

    @Override
    public void init() {
        quizService = getServiceFactory().getService(QUIZ_SERVICE, QuizService.class);
        questionService = getServiceFactory().getService(QUESTION_SERVICE, QuestionService.class);
        answerService = getServiceFactory().getService(ANSWER_SERVICE, AnswerService.class);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        final Object quizObject = request.getSession().getAttribute(SESSION_ATTRIBUTES.QUIZ);
        final Quiz quiz = (Quiz) quizObject;

        final List<Question> questions = quiz.getQuestions();
        request.setAttribute(QUESTIONS_LENGTH, questions.size());

        try {
            final Integer questionNumber = Integer.valueOf(request.getParameter(REQUEST_ATTRIBUTES.QUESTION_NUMBER));
            final Question firstQuestion = questions.get(questionNumber);
            request.setAttribute(QUESTION, firstQuestion.getText());
            final List<Answer> answers = firstQuestion.getAnswers();
            request.setAttribute(REQUEST_ATTRIBUTES.ANSWERS, answers);
        } catch (final NumberFormatException e) {
            response.sendRedirect(PAGES.NOT_FOUND);
            LOG.warn(e.getMessage(), e);
        }

        return PAGES.TEST;
    }
}
