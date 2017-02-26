package com.getprepared.controller.tutor;

import com.getprepared.annotation.Bean;
import com.getprepared.annotation.Inject;
import com.getprepared.domain.Question;
import com.getprepared.exception.EntityNotFoundException;
import com.getprepared.exception.ValidationException;
import com.getprepared.infrastructure.context.ApplicationContext;
import com.getprepared.service.QuestionService;
import com.getprepared.util.Validation;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.getprepared.constant.PageConstants.*;
import static com.getprepared.constant.ServerConstants.SERVICES.QUESTION_SERVICE;
import static com.getprepared.constant.UtilsConstant.VALIDATION;
import static com.getprepared.constant.WebConstants.INPUTS;

/**
 * Created by koval on 24.01.2017.
 */
@Bean("questionRemoveController")
public class QuestionRemoveController extends AbstractQuestionsController {

    private static final Logger LOG = Logger.getLogger(QuestionRemoveController.class);

    @Inject
    private QuestionService questionService;

    @Inject
    private Validation validation;

    @Override
    public String execute(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        try {
            final Long questionId = Long.parseLong(request.getParameter(INPUTS.QUESTION_ID));
            validation.validateId(questionId);

            final Question question = questionService.findById(questionId);
            validation.validateQuestion(question);
            
            questionService.remove(question);
            response.sendRedirect(LINKS.TUTOR_QUESTIONS);
            return REDIRECT;
        } catch (ValidationException | NumberFormatException | EntityNotFoundException e) {
            LOG.warn(e.getMessage(), e);
            response.sendRedirect(PAGES.NOT_FOUND);
            return REDIRECT;
        }
    }
}
