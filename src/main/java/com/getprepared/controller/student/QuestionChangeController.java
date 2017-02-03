package com.getprepared.controller.student;

import com.getprepared.constant.PageConstants;
import com.getprepared.domain.Question;
import com.getprepared.domain.Quiz;
import com.getprepared.exception.EntityNotFoundException;
import com.getprepared.service.QuizService;
import com.getprepared.utils.Validation;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.getprepared.constant.PageConstants.*;
import static com.getprepared.constant.PageConstants.LINKS;
import static com.getprepared.constant.PageConstants.REDIRECT;
import static com.getprepared.constant.ServerConstants.SERVICES.QUIZ_SERVICE;
import static com.getprepared.constant.UtilsConstant.VALIDATION;
import static com.getprepared.constant.WebConstants.REQUEST_ATTRIBUTES;
import static com.getprepared.constant.WebConstants.REQUEST_ATTRIBUTES.QUESTION;

/**
 * Created by koval on 03.02.2017.
 */
public class QuestionChangeController extends AbstractTestController {

    private static final Logger LOG = Logger.getLogger(QuestionChangeController.class);

    private QuizService quizService;
    private Validation validation;

    @Override
    public void init() {
        quizService = getServiceFactory().getService(QUIZ_SERVICE, QuizService.class);
        validation = getUtilsFactory().getUtil(VALIDATION, Validation.class);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        fillPage(request);

        final String stringNumber = request.getParameter(REQUEST_ATTRIBUTES.QUESTION_NUMBER);
        final Integer intNumber;

        try {
            intNumber = Integer.parseInt(stringNumber);
            // TODO add validation
            final Long longNumber = Long.parseLong(stringNumber);
            final Quiz quiz = quizService.findById(longNumber);
            final List<Question> questions = quiz.getQuestions();
            final Question question = questions.get(intNumber);
            request.setAttribute(QUESTION, question);
            return PAGES.STUDENT_TEST;
        } catch (EntityNotFoundException | NumberFormatException e) {
            LOG.warn(e.getMessage(), e);
            response.sendRedirect(LINKS.NOT_FOUND);
            return REDIRECT;
        }
    }
}
