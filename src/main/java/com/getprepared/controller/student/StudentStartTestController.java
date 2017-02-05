package com.getprepared.controller.student;

import com.getprepared.controller.dto.TestQuestion;
import com.getprepared.exception.ValidationException;
import com.getprepared.service.QuestionService;
import com.getprepared.utils.Validation;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.getprepared.constant.PageConstants.*;
import static com.getprepared.constant.ServerConstants.SERVICES.QUESTION_SERVICE;
import static com.getprepared.constant.UtilsConstant.VALIDATION;
import static com.getprepared.constant.WebConstants.INPUTS;
import static com.getprepared.constant.WebConstants.SESSION_ATTRIBUTES;

/**
 * Created by koval on 19.01.2017.
 */
public class StudentStartTestController extends AbstractStudentHomePageController {

    private static final Logger LOG = Logger.getLogger(StudentStartTestController.class);

    private QuestionService questionService;
    private Validation validation;

    @Override
    public void init() {
        questionService = getServiceFactory().getService(QUESTION_SERVICE, QuestionService.class);
        validation = getUtilsFactory().getUtil(VALIDATION, Validation.class);
    }

    @Override
    public String execute(final HttpServletRequest request, final HttpServletResponse response) throws IOException {

        final String quizId = request.getParameter(INPUTS.QUIZ_ID);

        try {
            final Long parsedQuizId = Long.parseLong(quizId);
            validation.validateId(parsedQuizId);
            request.getSession().setAttribute(SESSION_ATTRIBUTES.QUIZ_ID, parsedQuizId);

            final List<TestQuestion> test = questionService.startTest(parsedQuizId);
            request.getSession().setAttribute(SESSION_ATTRIBUTES.TEST, test);
            response.sendRedirect(LINKS.STUDENT_TEST);
            return REDIRECT;
        } catch (ValidationException | NumberFormatException e) {
            LOG.warn(e.getMessage(), e);
            response.sendRedirect(LINKS.NOT_FOUND);
            return REDIRECT;
        }
    }
}
