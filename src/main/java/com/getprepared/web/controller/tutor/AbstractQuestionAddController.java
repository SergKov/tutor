package com.getprepared.web.controller.tutor;

import com.getprepared.annotation.Inject;
import com.getprepared.web.controller.Controller;
import com.getprepared.persistence.domain.Type;
import com.getprepared.core.util.Messages;
import com.getprepared.web.validation.ValidationService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.getprepared.web.constant.PageConstants.*;
import static com.getprepared.web.constant.ValidationConstant.REGEX;
import static com.getprepared.web.constant.WebConstants.INPUTS;
import static com.getprepared.web.constant.WebConstants.REQUEST_ATTRIBUTES.*;
import static com.getprepared.web.constant.WebConstants.SESSION_ATTRIBUTES;

/**
 * Created by koval on 26.01.2017.
 */
public abstract class AbstractQuestionAddController implements Controller {

    private static final Logger LOG = Logger.getLogger(AbstractQuestionAddController.class);

    @Inject
    private Messages messages;

    protected void fillPage(final HttpServletRequest request) {

        final Long quizId = (Long) request.getSession().getAttribute(SESSION_ATTRIBUTES.QUIZ_ID);

        request.setAttribute(INPUTS.QUIZ_ID, quizId);

        request.setAttribute(TITLE, messages.getMessage(NAMES.ADD_QUESTION, request.getLocale()));
        request.setAttribute(ANSWER_TYPES, Type.values());
        request.setAttribute(ANSWER_TYPE_REGEX, REGEX.ANSWER_TYPE);
    }

    protected String returnPage(final HttpServletRequest request) throws IOException {
        fillPage(request);
        return PAGES.TUTOR_ADD_QUESTION;
    }
}
