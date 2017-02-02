package com.getprepared.controller.tutor;

import com.getprepared.controller.AbstractController;
import com.getprepared.domain.AnswerType;
import com.getprepared.exception.ValidationException;
import com.getprepared.utils.Validation;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.getprepared.constant.PageConstants.NAMES;
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

        final HttpSession session = request.getSession();
        final Object quizIdObject = session.getAttribute(SESSION_ATTRIBUTES.QUIZ_ID);

        try {
            final Long quizId = (Long) quizIdObject;
            validation.validateId(quizId);
            request.setAttribute(INPUTS.QUIZ_ID, quizId);
        } catch (ClassCastException | ValidationException e) {
            LOG.warn(e.getMessage(), e);
            throw new ValidationException(String.format("Illegal quizId %s", quizIdObject), e);
        }


        request.setAttribute(TITLE, getMessages().getMessage(NAMES.ADD_QUESTION, request.getLocale()));
        request.setAttribute(ANSWER_TYPES, AnswerType.values());
        request.setAttribute(ANSWER_TYPE_REGEX, REGEX.ANSWER_TYPE);
    }
}
