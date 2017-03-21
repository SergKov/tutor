package com.getprepared.web.controller.tutor;

import com.getprepared.annotation.Component;
import com.getprepared.annotation.Inject;
import com.getprepared.web.controller.common.AbstractQuizController;
import com.getprepared.persistence.domain.Quiz;
import com.getprepared.core.exception.EntityNotFoundException;
import com.getprepared.core.service.QuizService;
import com.getprepared.core.util.Messages;
import com.getprepared.web.validation.ValidationService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.getprepared.web.constant.PageConstants.*;
import static com.getprepared.web.constant.WebConstants.INPUTS;
import static com.getprepared.web.constant.WebConstants.REQUEST_ATTRIBUTES.ERROR_MSG;

/**
 * Created by koval on 24.01.2017.
 */
@Component("quizRemove")
public class QuizRemoveController extends AbstractQuizController {

    private static final Logger LOG = Logger.getLogger(QuizRemoveController.class);

    @Inject
    private QuizService quizService;

    @Inject
    private ValidationService validationService;

    @Inject
    private Messages messages;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        final String quizId = request.getParameter(INPUTS.QUIZ_ID);

        try {
            final Long parsedQuizId = Long.parseLong(quizId);
            // TODO add validation

            final Quiz quiz = quizService.findById(parsedQuizId);
            // TODO add validation

            quizService.remove(quiz);
            response.sendRedirect(LINKS.TUTOR_QUIZZES);
            return REDIRECT;
        } catch (final EntityNotFoundException e) {
            LOG.warn(e.getMessage(), e);
            request.setAttribute(ERROR_MSG, messages.getMessage(ERRORS.QUESTION_NOT_FOUND, request.getLocale()));
        } catch (final NumberFormatException e) {
            LOG.warn(e.getMessage(), e);
            response.sendRedirect(LINKS.NOT_FOUND);
            return REDIRECT;
        }

        fillPage(request, quizService);
        return PAGES.TUTOR_QUIZZES;
    }
}
