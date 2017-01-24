package com.getprepared.controller.common;

import com.getprepared.controller.AbstractController;
import com.getprepared.domain.Quiz;
import com.getprepared.service.QuizService;
import com.getprepared.utils.impl.CollectionUtils;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.getprepared.constant.PageConstants.NAMES;
import static com.getprepared.constant.WebConstants.REQUEST_ATTRIBUTES;
import static com.getprepared.constant.WebConstants.REQUEST_ATTRIBUTES.TITLE;

/**
 * Created by koval on 21.01.2017.
 */
public abstract class AbstractQuizController extends AbstractController {

    private static final Logger LOG = Logger.getLogger(AbstractQuizController.class);

    protected void fillPage(final HttpServletRequest request, final QuizService quizService) {

        request.setAttribute(TITLE, getMessages().getMessage(NAMES.QUIZZES, request.getLocale()));

        final List<Quiz> quizzes = quizService.findAll();
        if (!CollectionUtils.isEmpty(quizzes)) {
            request.setAttribute(REQUEST_ATTRIBUTES.QUIZZES, quizzes);
        }
    }
}
