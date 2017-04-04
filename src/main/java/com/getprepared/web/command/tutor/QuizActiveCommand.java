package com.getprepared.web.command.tutor;

import com.getprepared.annotation.Inject;
import com.getprepared.core.exception.EntityNotFoundException;
import com.getprepared.core.exception.QuizNotTerminatedException;
import com.getprepared.core.exception.QuizTerminatedException;
import com.getprepared.core.service.QuizService;
import com.getprepared.core.util.Messages;
import com.getprepared.persistence.domain.Quiz;
import com.getprepared.web.annotation.CommandMapping;
import com.getprepared.web.annotation.Controller;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.getprepared.web.constant.PageConstant.*;
import static com.getprepared.web.constant.WebConstant.INPUT;
import static com.getprepared.web.constant.WebConstant.REQUEST_ATTRIBUTE.ERROR_MSG;
import static javax.servlet.http.HttpServletResponse.SC_NOT_FOUND;

/**
 * Created by koval on 01.04.2017.
 */
@Controller
@CommandMapping(COMMAND.TUTOR_QUIZ_ACTIVE)
public class QuizActiveCommand extends AbstractQuizCommand { // TODO refacore

    private static final Logger LOG = Logger.getLogger(QuizActiveCommand.class);

    @Inject
    private QuizService quizService;

    @Inject
    private Messages messages;

    @Override
    public String execute(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        try {
            final Long quizId = Long.valueOf(request.getParameter(INPUT.QUIZ_ID));
            final Quiz quiz = quizService.findById(quizId);
            quizService.active(quiz.getId());
            response.sendRedirect(LINK.TUTOR_QUIZZES);
            return REDIRECT;
        } catch (EntityNotFoundException | NumberFormatException | QuizTerminatedException e) {
            LOG.warn(e.getMessage(), e);
            response.sendError(SC_NOT_FOUND);
            return REDIRECT;
        } catch (final QuizNotTerminatedException e) {
            LOG.warn(e.getMessage(), e);
            request.setAttribute(ERROR_MSG, messages.getMessage(ERROR.QUIZ_EMPTY, request.getLocale()));
        }

        try {
            fillPage(request, quizService);
        } catch (final EntityNotFoundException e) {
            LOG.warn(e.getMessage(), e);
            response.sendError(SC_NOT_FOUND);
            return REDIRECT;
        }

        return PAGE.TUTOR_QUIZZES;
    }
}
