package com.getprepared.controller.tutor;

import com.getprepared.controller.AbstractController;
import com.getprepared.domain.AnswerType;
import com.getprepared.exception.ValidationException;
import com.getprepared.utils.Validation;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.getprepared.constant.PageConstants.*;
import static com.getprepared.constant.UtilsConstant.REGEX;
import static com.getprepared.constant.WebConstants.INPUTS;
import static com.getprepared.constant.WebConstants.REQUEST_ATTRIBUTES.*;
import static com.getprepared.constant.WebConstants.SESSION_ATTRIBUTES;

/**
 * Created by koval on 26.01.2017.
 */
public abstract class AbstractQuestionAddController extends AbstractController {

    private static final Logger LOG = Logger.getLogger(AbstractQuestionAddController.class);

    protected void fillPage(final HttpServletRequest request, final Validation validation) throws ValidationException {

        final Object quizId = request.getSession().getAttribute(SESSION_ATTRIBUTES.QUIZ_ID);
        final Long parsedQuizId = (Long) quizId;

        try {
            validation.validateId(parsedQuizId);
            request.setAttribute(INPUTS.QUIZ_ID, parsedQuizId);
        } catch (ValidationException | ClassCastException e) {
            LOG.warn(e.getMessage(), e);
            throw new ValidationException(String.format("Illegal quizId %s", quizId), e);
        }

        request.setAttribute(TITLE, getMessages().getMessage(NAMES.ADD_QUESTION, request.getLocale()));
        request.setAttribute(ANSWER_TYPES, AnswerType.values());
        request.setAttribute(ANSWER_TYPE_REGEX, REGEX.ANSWER_TYPE);
    }

    protected String fillPage(final HttpServletRequest request, final HttpServletResponse response,
                              final Validation validation) throws IOException {
        try {
            fillPage(request, validation);
        } catch (final ValidationException e) {
            LOG.warn(e.getMessage(), e);
            response.sendRedirect(LINKS.NOT_FOUND);
            return REDIRECT;
        }

        return PAGES.TUTOR_ADD_QUESTION;
    }
}
