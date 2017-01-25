package com.getprepared.controller.tutor;

import com.getprepared.constant.PageConstants;
import com.getprepared.constant.WebConstants;
import com.getprepared.controller.AbstractController;
import com.getprepared.domain.Quiz;
import com.getprepared.exception.ValidationException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import static com.getprepared.constant.PageConstants.LINKS.ADD_QUIZ;
import static com.getprepared.constant.PageConstants.NAMES;
import static com.getprepared.constant.UtilsConstant.REGEX;
import static com.getprepared.constant.WebConstants.*;
import static com.getprepared.constant.WebConstants.REQUEST_ATTRIBUTES.TITLE;

/**
 * Created by koval on 22.01.2017.
 */
public abstract class AbstractQuizAddController extends AbstractController {

    private static final Logger LOG = Logger.getLogger(AbstractQuizAddController.class);

    protected void fillPage(final HttpServletRequest request) {
        request.setAttribute(TITLE, getMessages().getMessage(NAMES.ADD_QUIZ, request.getLocale()));
        request.setAttribute(ADD_QUIZ, REGEX.QUIZ_NAME);
    }

    protected Quiz convertInputToQuiz(final HttpServletRequest request) throws ValidationException {
        try {
            final String name = request.getParameter(INPUTS.QUIZ_NAME);
            return new Quiz(name);
        } catch (final Exception e) {
            LOG.warn(e.getMessage(), e);
            throw new ValidationException("Failed to validate quiz", e);
        }
    }
}
