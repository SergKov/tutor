package com.getprepared.web.command.tutor;

import com.getprepared.annotation.Inject;
import com.getprepared.core.exception.EntityNotFoundException;
import com.getprepared.core.service.QuestionService;
import com.getprepared.core.service.QuizService;
import com.getprepared.core.util.Messages;
import com.getprepared.persistence.domain.Question;
import com.getprepared.web.annotation.CommandMapping;
import com.getprepared.web.annotation.Controller;
import com.getprepared.web.validation.ValidationService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.getprepared.web.constant.ApplicationConstant.*;
import static com.getprepared.web.constant.PropertyConstant.KEY;
import static com.getprepared.web.constant.WebConstant.INPUT;
import static com.getprepared.web.constant.WebConstant.REQUEST_ATTRIBUTE.QUESTION;
import static com.getprepared.web.constant.WebConstant.REQUEST_ATTRIBUTE.TITLE;
import static com.getprepared.web.constant.WebConstant.SESSION_ATTRIBUTE;
import static javax.servlet.http.HttpServletResponse.SC_NOT_FOUND;
import static org.apache.commons.lang3.StringUtils.isEmpty;
import static org.apache.commons.lang3.StringUtils.isNumeric;

/**
 * Created by koval on 25.01.2017.
 */
@Controller
@CommandMapping(LINK.TUTOR_QUESTIONS)
public class QuestionsPageCommand extends AbstractQuestionsCommand {

    private static final Logger LOG = Logger.getLogger(QuestionsPageCommand.class);

    @Inject
    private QuizService quizService;

    @Inject
    private QuestionService questionService;

    @Inject
    private ValidationService validationService;

    @Inject
    private Messages messages;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        if (request.getSession().getAttribute(SESSION_ATTRIBUTE.QUIZ_ID) == null) {
            response.sendError(SC_NOT_FOUND);
            return REDIRECT;
        }

        final String questionIdString = request.getParameter(INPUT.QUESTION_ID);

        if (isEmpty(questionIdString)) {
            try {
                fillPage(request, quizService, questionService);
            } catch (final EntityNotFoundException e) {
                LOG.warn(e.getMessage(), e);
                response.sendError(SC_NOT_FOUND);
                return REDIRECT;
            }
            return PATH.TUTOR_QUESTIONS;
        }

        if (isNumeric(questionIdString)) {
            try {
                request.setAttribute(TITLE, messages.getMessage(KEY.QUESTION, request.getLocale()));
                final Long questionId = Long.valueOf(questionIdString);
                final Question question = questionService.findById(questionId);
                request.setAttribute(QUESTION, question);
            } catch (final EntityNotFoundException e) {
                LOG.warn(e.getMessage(), e);
                response.sendError(SC_NOT_FOUND);
                return REDIRECT;
            }
            return PATH.TUTOR_QUESTION;
        }

        response.sendError(SC_NOT_FOUND);
        return REDIRECT;
    }
}
