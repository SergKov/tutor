package com.getprepared.web.controller.tutor;

import com.getprepared.annotation.Inject;
import com.getprepared.core.exception.EntityNotFoundException;
import com.getprepared.core.service.QuizService;
import com.getprepared.core.util.Messages;
import com.getprepared.persistence.domain.Quiz;
import com.getprepared.web.annotation.Controller;
import com.getprepared.web.controller.common.AbstractQuizCommand;
import com.getprepared.web.validation.ValidationService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.getprepared.web.constant.PageConstants.LINKS;
import static com.getprepared.web.constant.PageConstants.REDIRECT;
import static com.getprepared.web.constant.WebConstants.INPUTS;

/**
 * Created by koval on 24.01.2017.
 */
@Controller
public class QuizRemoveCommand extends AbstractQuizCommand {

    private static final Logger LOG = Logger.getLogger(QuizRemoveCommand.class);

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

            final Quiz quiz = quizService.findById(parsedQuizId);
            quizService.remove(quiz);

            response.sendRedirect(LINKS.TUTOR_QUIZZES);
        } catch (final EntityNotFoundException | NumberFormatException e) {
            LOG.warn(e.getMessage(), e);
            response.sendRedirect(LINKS.NOT_FOUND);
        }
        return REDIRECT;
    }
}
