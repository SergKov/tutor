package com.getprepared.web.command.tutor;

import com.getprepared.annotation.Inject;
import com.getprepared.core.exception.EntityNotFoundException;
import com.getprepared.core.service.QuestionService;
import com.getprepared.persistence.domain.Question;
import com.getprepared.web.annotation.Controller;
import com.getprepared.web.annotation.RequestMapping;
import com.getprepared.web.annotation.RequestMethod;
import com.getprepared.web.validation.ValidationService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.getprepared.web.constant.PageConstants.*;
import static com.getprepared.web.constant.WebConstants.INPUTS;

/**
 * Created by koval on 24.01.2017.
 */
@Controller
@RequestMapping(value = FORMS.TUTOR_REMOVE_QUESTION, method = RequestMethod.POST)
public class QuestionRemoveCommand extends AbstractQuestionsCommand {

    private static final Logger LOG = Logger.getLogger(QuestionRemoveCommand.class);

    @Inject
    private QuestionService questionService;

    @Inject
    private ValidationService validationService;

    @Override
    public String execute(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        try {
            final Long questionId = Long.parseLong(request.getParameter(INPUTS.QUESTION_ID));
            final Question question = questionService.findById(questionId);
            questionService.remove(question);
            response.sendRedirect(LINKS.TUTOR_QUESTIONS);
            return REDIRECT;
        } catch (NumberFormatException | EntityNotFoundException e) {
            LOG.warn(e.getMessage(), e);
            response.sendRedirect(PAGES.NOT_FOUND);
            return REDIRECT;
        }
    }
}
