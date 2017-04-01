package com.getprepared.web.command.tutor;

import com.getprepared.annotation.Inject;
import com.getprepared.core.exception.EntityNotFoundException;
import com.getprepared.core.service.QuizService;
import com.getprepared.core.util.Messages;
import com.getprepared.persistence.domain.Quiz;
import com.getprepared.web.annotation.CommandMapping;
import com.getprepared.web.annotation.Controller;
import com.getprepared.web.command.common.AbstractQuizCommand;
import com.getprepared.web.validation.ValidationService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.getprepared.web.constant.PageConstant.*;
import static com.getprepared.web.constant.WebConstant.INPUT;
import static javax.servlet.http.HttpServletResponse.SC_NOT_FOUND;

/**
 * Created by koval on 24.01.2017.
 */
@Controller
@CommandMapping(COMMAND.TUTOR_QUIZ_REMOVE)
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

        final String quizId = request.getParameter(INPUT.QUIZ_ID);

        try {
            final Long parsedQuizId = Long.parseLong(quizId);

            final Quiz quiz = quizService.findById(parsedQuizId);
            quizService.remove(quiz);

            response.sendRedirect(LINK.TUTOR_QUIZZES);
        } catch (EntityNotFoundException | NumberFormatException e) {
            LOG.warn(e.getMessage(), e);
            response.sendError(SC_NOT_FOUND);
        }
        return REDIRECT;
    }
}
