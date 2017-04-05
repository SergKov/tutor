package com.getprepared.web.command.tutor;

import com.getprepared.annotation.Inject;
import com.getprepared.core.exception.EntityNotFoundException;
import com.getprepared.core.exception.QuizTerminatedException;
import com.getprepared.core.service.QuestionService;
import com.getprepared.persistence.domain.Question;
import com.getprepared.web.annotation.CommandMapping;
import com.getprepared.web.annotation.Controller;
import com.getprepared.web.validation.ValidationService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.getprepared.web.constant.ApplicationConstant.*;
import static com.getprepared.web.constant.WebConstant.INPUT;
import static javax.servlet.http.HttpServletResponse.SC_NOT_FOUND;

/**
 * Created by koval on 24.01.2017.
 */
@Controller
@CommandMapping(COMMAND.TUTOR_QUESTION_REMOVE)
public class QuestionRemoveCommand extends AbstractQuestionsCommand {

    private static final Logger LOG = Logger.getLogger(QuestionRemoveCommand.class);

    @Inject
    private QuestionService questionService;

    @Inject
    private ValidationService validationService;

    @Override
    public String execute(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        try {
            final Long questionId = Long.parseLong(request.getParameter(INPUT.QUESTION_ID));
            final Question question = questionService.findById(questionId);
            questionService.remove(question.getId());
            response.sendRedirect(LINK.TUTOR_QUESTIONS);
        } catch (EntityNotFoundException | NumberFormatException | QuizTerminatedException e) {
            LOG.warn(e.getMessage(), e);
            response.sendError(SC_NOT_FOUND);
        }
        return REDIRECT;
    }
}
