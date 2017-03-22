package com.getprepared.web.controller.common;

import com.getprepared.annotation.Inject;
import com.getprepared.core.service.QuizService;
import com.getprepared.core.util.Messages;
import com.getprepared.persistence.domain.Quiz;
import com.getprepared.web.controller.Command;
import org.apache.commons.collections4.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.getprepared.web.constant.PageConstants.NAMES;
import static com.getprepared.web.constant.WebConstants.REQUEST_ATTRIBUTES;
import static com.getprepared.web.constant.WebConstants.REQUEST_ATTRIBUTES.TITLE;

/**
 * Created by koval on 21.01.2017.
 */
public abstract class AbstractQuizCommand implements Command {

    @Inject
    private Messages messages;

    protected void fillPage(final HttpServletRequest request, final QuizService quizService) {

        request.setAttribute(TITLE, messages.getMessage(NAMES.QUIZZES, request.getLocale()));

        final List<Quiz> quizzes = quizService.findAll();
        if (!CollectionUtils.isEmpty(quizzes)) {
            request.setAttribute(REQUEST_ATTRIBUTES.QUIZZES, quizzes);
        }
    }
}
