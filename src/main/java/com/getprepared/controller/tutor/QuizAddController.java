package com.getprepared.controller.tutor;

import com.getprepared.annotation.Component;
import com.getprepared.annotation.Inject;
import com.getprepared.constant.WebConstants;
import com.getprepared.domain.Quiz;
import com.getprepared.exception.EntityExistsException;
import com.getprepared.service.QuizService;
import com.getprepared.util.Messages;
import com.getprepared.validation.ValidationService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.getprepared.constant.PageConstants.*;
import static com.getprepared.constant.WebConstants.REQUEST_ATTRIBUTES;
import static com.getprepared.constant.WebConstants.REQUEST_ATTRIBUTES.ERROR_MSG;

/**
 * Created by koval on 22.01.2017.
 */
@Component("quizAdd")
public class QuizAddController extends AbstractQuizAddController {

    private static final Logger LOG = Logger.getLogger(QuizAddController.class);

    @Inject
    private QuizService quizService;

    @Inject
    private ValidationService validationService;

    @Inject
    private Messages messages;

    @Override
    public String execute(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        final Quiz quiz = new Quiz();
        final String quizName = request.getParameter(WebConstants.INPUTS.QUIZ_NAME);
        try {
            quiz.setName(quizName);
            // TODO add validation
            
            quizService.save(quiz);
            response.sendRedirect(LINKS.TUTOR_QUIZZES);
            return REDIRECT;
        } catch (final EntityExistsException e) {
            LOG.warn(e.getMessage(), e);
            request.setAttribute(ERROR_MSG, messages.getMessage(ERRORS.QUIZ_EXISTS, request.getLocale()));
        }
        request.setAttribute(REQUEST_ATTRIBUTES.QUIZ_NAME, quiz.getName());

        fillPage(request);
        return PAGES.TUTOR_ADD_QUIZ;
    }
}
