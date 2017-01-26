package com.getprepared.controller.tutor;

import com.getprepared.exception.ValidationException;
import com.getprepared.utils.Validation;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.getprepared.constant.PageConstants.LINKS;
import static com.getprepared.constant.PageConstants.REDIRECT;
import static com.getprepared.constant.UtilsConstant.VALIDATION;
import static com.getprepared.constant.WebConstants.INPUTS.QUIZ_ID;
import static com.getprepared.constant.WebConstants.SESSION_ATTRIBUTES;

/**
 * Created by koval on 24.01.2017.
 */
public class QuizQuestionsController extends AbstractQuizController {

    private static final Logger LOG = Logger.getLogger(QuizQuestionsController.class);

    private Validation validation;

    @Override
    public void init() {
        validation = getUtilsFactory().getUtil(VALIDATION, Validation.class);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        final String quizIdString = request.getParameter(QUIZ_ID);

        try {
            final Long quizId = Long.parseLong(quizIdString);
            validation.validateId(quizId);
            request.getSession().setAttribute(SESSION_ATTRIBUTES.QUIZ_ID, quizId);
            response.sendRedirect(LINKS.TUTOR_QUESTIONS);
        } catch (ValidationException | NumberFormatException e) {
            LOG.warn(e.getMessage(), e);
            response.sendRedirect(LINKS.NOT_FOUND);
        }

        return REDIRECT;
    }
}
