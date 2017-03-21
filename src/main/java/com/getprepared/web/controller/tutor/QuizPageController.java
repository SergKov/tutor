package com.getprepared.web.controller.tutor;

import com.getprepared.annotation.Component;
import com.getprepared.annotation.Inject;
import com.getprepared.web.controller.common.AbstractQuizController;
import com.getprepared.core.service.QuizService;
import com.getprepared.web.validation.ValidationService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.getprepared.web.constant.PageConstants.*;
import static com.getprepared.web.constant.WebConstants.INPUTS.QUIZ_ID;
import static com.getprepared.web.constant.WebConstants.SESSION_ATTRIBUTES;
import static org.apache.commons.lang3.StringUtils.*;

/**
 * Created by koval on 22.01.2017.
 */
@Component("quizPage")
public class QuizPageController extends AbstractQuizController {

    private static final Logger LOG = Logger.getLogger(QuizPageController.class);

    @Inject
    private QuizService quizService;

    @Inject
    private ValidationService validationService;

    @Override
    public String execute(final HttpServletRequest request, final HttpServletResponse response) throws IOException {

        final String quizId = request.getParameter(QUIZ_ID);

        if (isNumeric(quizId)) {
            final Long parsedQuizId = Long.valueOf(quizId);
            // TODO add validation

            request.getSession().setAttribute(SESSION_ATTRIBUTES.QUIZ_ID, parsedQuizId);
            response.sendRedirect(LINKS.TUTOR_QUESTIONS);
            return REDIRECT;
        } else {
            request.getSession().removeAttribute(SESSION_ATTRIBUTES.QUIZ_ID);
            fillPage(request, quizService);
            return PAGES.TUTOR_QUIZZES;
        }
    }
}
