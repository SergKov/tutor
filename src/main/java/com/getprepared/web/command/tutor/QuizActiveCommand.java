package com.getprepared.web.command.tutor;

import com.getprepared.annotation.Inject;
import com.getprepared.core.exception.EntityNotFoundException;
import com.getprepared.core.exception.QuizTerminatedException;
import com.getprepared.core.service.QuizService;
import com.getprepared.persistence.domain.Quiz;
import com.getprepared.web.annotation.CommandMapping;
import com.getprepared.web.annotation.Controller;
import com.getprepared.web.command.Command;
import com.getprepared.web.constant.WebConstant;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.getprepared.web.constant.PageConstant.COMMAND;
import static com.getprepared.web.constant.PageConstant.REDIRECT;
import static com.getprepared.web.constant.WebConstant.*;
import static com.getprepared.web.constant.WebConstant.SESSION_ATTRIBUTE;
import static javax.servlet.http.HttpServletResponse.SC_NOT_FOUND;

/**
 * Created by koval on 01.04.2017.
 */
@Controller
@CommandMapping(COMMAND.TUTOR_QUIZ_ACTIVE)
public class QuizActiveCommand implements Command {

    private static final Logger LOG = Logger.getLogger(QuizActiveCommand.class);

    @Inject
    private QuizService quizService;

    @Override
    public String execute(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        try {
            final Long quizId = Long.valueOf(request.getParameter(INPUT.QUIZ_ID));
            final Quiz quiz = quizService.findById(quizId);
            quizService.active(quiz);
        } catch (EntityNotFoundException | QuizTerminatedException | NumberFormatException e) {
            LOG.warn(e.getMessage(), e);
            response.sendError(SC_NOT_FOUND);
        }
        return REDIRECT;
    }
}
