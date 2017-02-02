package com.getprepared.controller.tutor;

import com.getprepared.constant.WebConstants;
import com.getprepared.constant.WebConstants.REQUEST_ATTRIBUTES;
import com.getprepared.controller.AbstractController;
import com.getprepared.domain.Question;
import com.getprepared.domain.Quiz;
import com.getprepared.exception.EntityNotFoundException;
import com.getprepared.exception.ValidationException;
import com.getprepared.service.QuestionService;
import com.getprepared.service.QuizService;
import com.getprepared.utils.Validation;
import com.getprepared.utils.impl.CollectionUtils;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.getprepared.constant.PageConstants.ERRORS;
import static com.getprepared.constant.PageConstants.NAMES;
import static com.getprepared.constant.WebConstants.*;
import static com.getprepared.constant.WebConstants.REQUEST_ATTRIBUTES.ERROR_MSG;
import static com.getprepared.constant.WebConstants.REQUEST_ATTRIBUTES.TITLE;
import static com.getprepared.constant.WebConstants.SESSION_ATTRIBUTES;

/**
 * Created by koval on 25.01.2017.
 */
public abstract class AbstractQuestionsController extends AbstractController {

    private static final Logger LOG = Logger.getLogger(AbstractQuestionsController.class);

    protected void fillPage(final HttpServletRequest request, final QuizService quizService,
                            final QuestionService questionService) throws ValidationException {

        request.setAttribute(TITLE, getMessages().getMessage(NAMES.QUESTIONS, request.getLocale()));

        final Object quizIdObject = request.getAttribute(INPUTS.QUIZ_ID);

        try {
            final Long quizId = (Long) quizIdObject;
            final Quiz quiz = quizService.findById(quizId);
            request.setAttribute(REQUEST_ATTRIBUTES.QUIZ, quiz);
            final List<Question> questions = questionService.findByQuizId(quizId);
            if (!CollectionUtils.isEmpty(questions)) {
                request.setAttribute(REQUEST_ATTRIBUTES.QUESTIONS, questions);
            }
        } catch (final Exception e) {
            LOG.warn(e.getMessage(), e);
            throw new ValidationException(String.format("Failed to validate quizId %s", quizIdObject), e);
        }
    }
}
