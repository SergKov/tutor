package com.getprepared.controller.tutor;

import com.getprepared.annotation.Bean;
import com.getprepared.annotation.Inject;
import com.getprepared.controller.Controller;
import com.getprepared.controller.common.AbstractQuizController;
import com.getprepared.exception.ValidationException;
import com.getprepared.service.QuizService;
import com.getprepared.util.Validation;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.getprepared.constant.PageConstants.*;
import static com.getprepared.constant.ServerConstants.SERVICES.QUIZ_SERVICE;
import static com.getprepared.constant.UtilsConstant.VALIDATION;
import static com.getprepared.constant.WebConstants.INPUTS.QUIZ_ID;
import static com.getprepared.constant.WebConstants.SESSION_ATTRIBUTES;

/**
 * Created by koval on 22.01.2017.
 */
@Bean("quizPage")
public class QuizPageController extends AbstractQuizController {

    private static final Logger LOG = Logger.getLogger(QuizPageController.class);

    @Inject
    private QuizService quizService;

    @Inject
    private Validation validation;

    @Override
    public String execute(final HttpServletRequest request, final HttpServletResponse response) throws IOException {

        final String quizId = request.getParameter(QUIZ_ID);

        if (StringUtils.isNumeric(quizId)) {
            try {
                final Long parsedQuizId = Long.valueOf(quizId);
                validation.validateId(parsedQuizId);

                request.getSession().setAttribute(SESSION_ATTRIBUTES.QUIZ_ID, parsedQuizId);
                response.sendRedirect(LINKS.TUTOR_QUESTIONS);
            } catch (final ValidationException e) {
                LOG.warn(e.getMessage(), e);
                response.sendRedirect(PAGES.NOT_FOUND);
            }
            return REDIRECT;
        } else {
            request.getSession().removeAttribute(SESSION_ATTRIBUTES.QUIZ_ID);
            fillPage(request, quizService);
            return PAGES.TUTOR_QUIZZES;
        }
    }
}
